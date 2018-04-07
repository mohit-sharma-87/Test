package com.codelife.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.codelife.test.AppApplication;
import com.codelife.test.R;
import com.codelife.test.mvp.IPresenter;
import com.codelife.test.mvp.IView;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by mohitsharma on 31/08/17.
 */

public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findViewById(R.id.bt_start).setOnClickListener(v -> isAppReady());
    }

    public void isAppReady() {
        ((AppApplication)getApplication()).getCompletable().subscribe(observer);
    }


    CompletableObserver observer = new CompletableObserver() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onComplete() {
            gotToNextScreen();
        }

        @Override
        public void onError(Throwable e) {
            // Case not possible.
        }
    };


    private void gotToNextScreen() {
        finish();
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
    }


    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected IView getPresenterView() {
        return null;
    }

}

