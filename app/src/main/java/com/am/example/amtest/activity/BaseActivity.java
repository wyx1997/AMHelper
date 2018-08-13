package com.am.example.amtest.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.am.example.amtest.entity.MyEventBusMsg;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    protected Unbinder unbinder;

    protected ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN|
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        if(null != getIntent()){
            handleIntent(getIntent());
        }
        if(null != savedInstanceState){
            handleSavedInstanceState(savedInstanceState);
        }
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected void initToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
    }


    protected void registerEventBus(BaseActivity activity){
        EventBus.getDefault().register(activity);
    }

    protected void removeStickyEvent(MyEventBusMsg event){
        EventBus.getDefault().removeStickyEvent(event);
    }

    protected void unRegisterEventBus(BaseActivity activity){
        if(EventBus.getDefault().isRegistered(activity)){
            EventBus.getDefault().unregister(activity);
        }
    }

    protected void removeAllEvent(){
        EventBus.getDefault().removeAllStickyEvents();
    }

    protected void handleSavedInstanceState(Bundle savedInstanceState){}

    protected void handleIntent(Intent intent){}

    protected abstract int getLayoutId();

    protected abstract void init();
}
