package com.codelife.test.dataService;



import com.codelife.test.models.BaseEntity;
import com.codelife.test.models.RequestStatus;

import java.lang.reflect.ParameterizedType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mohit on 20/02/16.
 */
public abstract class RetrofitCallBack<T extends BaseEntity> implements Callback<T> {

    private T t;

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (response.body() != null && response.body().getResponseCode() == 200) {
                response.body().setRequestStatus(RequestStatus.OK);
            } else {
                response.body().setRequestStatus(RequestStatus.SERVER_ERROR);
            }
            handleResponse(call, response.body());
        } else {
            t = getInstance();
            t.setRequestStatus(RequestStatus.UNEXPECTED);
            t.setMessage("Unexpected Error. Please contact admin");
            handleResponse(call, t);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        t = getInstance();
        t.setRequestStatus(RequestStatus.UNEXPECTED);
        handleResponse(call, t);
    }

    private T getInstance() {
        try {
            t = (T) ((Class) ((ParameterizedType) this.getClass().
                    getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
        } catch (InstantiationException e) {
            t = null;
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            t = null;
            e.printStackTrace();
        }
        return t;
    }

    public abstract void handleResponse(Call<T> call, T t);
}
