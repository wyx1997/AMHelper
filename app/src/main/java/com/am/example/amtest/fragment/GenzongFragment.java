package com.am.example.amtest.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.am.example.amtest.R;
import com.am.example.amtest.adapter.MyRvAdapter;
import com.am.example.amtest.entity.MyMultiEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

public class GenzongFragment extends BaseFragment {

    @BindView(R.id.genzong_rv)
    RecyclerView recyclerView;

    @BindArray(R.array.genzong_array)
    String[] textArray;

    private MyRvAdapter mAdapter;

    private List<MyMultiEntity> mMultiEntities = new ArrayList<>();

    public static GenzongFragment newInstance(){
        return new GenzongFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_genzong;
    }

    @Override
    protected void initView(LayoutInflater layoutInflater, Bundle savedInstanceState, ViewGroup container) {
        if(mMultiEntities.size() == 0){
            for(String item : textArray){
                mMultiEntities.add(new MyMultiEntity(item));
            }
        }
        if(mAdapter == null){
            mAdapter = new MyRvAdapter(fatherActivity, mMultiEntities);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(fatherActivity));
            recyclerView.setAdapter(mAdapter);
        }
    }
}
