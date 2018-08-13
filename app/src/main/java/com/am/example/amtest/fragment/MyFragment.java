package com.am.example.amtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.example.amtest.R;
import com.am.example.amtest.adapter.MyRvAdapter;
import com.am.example.amtest.entity.MyMultiEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

public class MyFragment extends BaseFragment {

    @BindView(R.id.my_rv)
    RecyclerView recyclerView;

    @BindArray(R.array.my_array)
    String[] mArray;

    private MyRvAdapter mAdapter;

    private List<MyMultiEntity> mMultiEntities = new ArrayList<>();

    public static MyFragment newInstance(){
        return new MyFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(LayoutInflater layoutInflater, Bundle savedInstanceState, ViewGroup container) {
        if(mMultiEntities.size() == 0){
            for(String item : mArray){
                mMultiEntities.add(new MyMultiEntity(item));
            }
        }
        if(mAdapter == null){
            mAdapter = new MyRvAdapter(fatherActivity, mMultiEntities);
            mAdapter.addHeaderView(layoutInflater.inflate(R.layout.header_view_my_rv, container, false));
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(fatherActivity));
            recyclerView.setAdapter(mAdapter);
        }
    }
}
