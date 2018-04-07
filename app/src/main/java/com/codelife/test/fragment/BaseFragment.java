package com.codelife.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.codelife.test.mvp.IPresenter;
import com.codelife.test.mvp.IView;


/**
 * Created by mohitsharma on 12/12/17.
 */

public abstract class BaseFragment extends Fragment {

    private boolean isRegistered = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startPresenter();
    }

    @Override
    public void onStart() {
        super.onStart();
        startPresenter();
    }

    @Override
    public void onStop() {
        stopPresenter();
        super.onStop();
    }

    private void stopPresenter() {
        if (getPresenter() != null) {
            getPresenter().stop();
            isRegistered = false;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            stopPresenter();
        } else {
            startPresenter();
        }
    }

    protected abstract IPresenter getPresenter();

    protected abstract IView getPresenterView();

    private void startPresenter() {
        if (!isRegistered && getPresenter() != null) {
            getPresenter().start(getPresenterView());
            isRegistered = true;
        }
    }
}