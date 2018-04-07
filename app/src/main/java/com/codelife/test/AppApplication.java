package com.codelife.test;

import android.app.Application;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppApplication extends Application {

    private Completable completable;

    @Override
    public void onCreate() {
        super.onCreate();
        completable = Completable.fromAction(() -> {
            if (!BaseFactory.getInstance().isAppReady())
                BaseFactory.getInstance().setupFactory(getApplicationContext());
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


    public Completable getCompletable() {
        return completable;
    }
}
