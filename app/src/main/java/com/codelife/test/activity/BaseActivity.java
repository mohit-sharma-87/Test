package com.codelife.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.codelife.test.mvp.IPresenter;
import com.codelife.test.mvp.IView;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setPresenter();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getPresenter() != null)
            getPresenter().stop();
    }

    protected abstract IPresenter getPresenter();

    protected abstract IView getPresenterView();

    private void setPresenter() {
        if (getPresenter() != null) {
            getPresenter().start(getPresenterView());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


}