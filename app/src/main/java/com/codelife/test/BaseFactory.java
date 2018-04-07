package com.codelife.test;

import android.content.Context;

import com.codelife.test.dataService.DataServiceFactory;
import com.codelife.test.feature.Feature;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mohit on 21/10/16.
 */
public class BaseFactory {

    private EventBus mEventBus;
    private final HashMap<String, String> mRequestHeaderMap;
    private DataServiceFactory mDataServiceFactory;
    private Feature feature;
    private boolean isAppReady;
    private static BaseFactory instance;

    private BaseFactory() {
        mEventBus = getEventBus();
        mEventBus.register(this);
        mRequestHeaderMap = new HashMap<>();
    }

    public static BaseFactory getInstance(){
        if (instance == null){
            instance = new BaseFactory();
        }

        return instance;
    }


    public void setupFactory(Context applicationContext) {
        setupDataService();
        loadApplicationHeader();
        isAppReady = true;
    }

    private void setupDataService() {
        getDataServiceFactory();
    }

    private void loadApplicationHeader() {
    }

    public Feature getFeature() {
        if (feature == null) {
            feature = new Feature(getDataServiceFactory().getDataService());
        }
        return feature;
    }


    @Subscribe
    public void onHeaderReceived(Map.Entry<String, String> entry) {
        mRequestHeaderMap.put(entry.getKey(), entry.getValue());
    }


    private DataServiceFactory getDataServiceFactory() {
        if (mDataServiceFactory == null) {
            mDataServiceFactory = DataServiceFactory.getInstance(mEventBus, mRequestHeaderMap);
        }
        return mDataServiceFactory;
    }

    public EventBus getEventBus() {
        if (mEventBus == null) {
            mEventBus = EventBus.builder()
                    .eventInheritance(false)
                    .throwSubscriberException(false)
                    .build();
        }
        return mEventBus;
    }

    public boolean isAppReady() {
        return isAppReady;
    }
}

