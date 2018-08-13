package com.am.example.amtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.am.example.amtest.R;
import com.am.example.amtest.adapter.LuntanViewPagerAdapter;
import com.am.example.amtest.entity.ViewPagerEventMsg;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class LuntanFragment extends BaseFragment {

    @BindView(R.id.luntan_view_pager)
    ViewPager viewPager;

    private ViewPagerEventMsg pagerEventMsg;

    private LuntanViewPagerAdapter pagerAdapter;

    public static LuntanFragment newInstance(){
        return new LuntanFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerEventBus(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_luntan;
    }

    @Override
    protected void initView(LayoutInflater layoutInflater, Bundle savedInstanceState, ViewGroup container) {
        if(pagerAdapter == null){
            pagerAdapter = new LuntanViewPagerAdapter(getChildFragmentManager(), fatherActivity);
            viewPager.setAdapter(pagerAdapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if(pagerEventMsg == null) {
                        pagerEventMsg = new ViewPagerEventMsg(position);
                    } else {
                        pagerEventMsg.setPosition(position);
                    }
                    EventBus.getDefault().post(pagerEventMsg);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegisterEventBus(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPosMsg(ViewPagerEventMsg msg){
        removeStickyEvent(msg);
        if(msg.getPosition() == viewPager.getCurrentItem()){
            return;
        }
        viewPager.setCurrentItem(msg.getPosition());
    }
}
