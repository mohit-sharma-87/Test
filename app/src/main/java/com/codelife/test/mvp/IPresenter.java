package com.codelife.test.mvp;


public interface IPresenter<V extends IView> {

    void start(V view);

    void stop();

}
