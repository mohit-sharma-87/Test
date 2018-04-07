package com.codelife.test.dataService;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import retrofit2.Retrofit;

/**
 * Created by Mohit Sharma on 08/02/16.
 */
public class DataServiceFactory {

    private static DataServiceFactory sDataService;
    private Retrofit mRestClient;
    private EventBus mEventBus;

    private DataServiceFactory() {
    }

    private DataServiceFactory(EventBus eventBus, Retrofit restClient) {
        mRestClient = restClient;
        mEventBus = eventBus;
    }

    public static DataServiceFactory getInstance(EventBus eventBus, HashMap<String, String> requestHeaderMap) {
        if (sDataService == null) {
            sDataService = new DataServiceFactory(eventBus, NetworkClient.getRestAdapter(requestHeaderMap));
        }
        return sDataService;
    }

    private <S> S getClient(Class<S> serviceClass) {
        return mRestClient.create(serviceClass);
    }

    public DataService getDataService() {
        return new DataService(mEventBus, getClient(EndPoints.class));
    }

}

