package com.am.example.amtest.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.am.example.amtest.R;
import com.am.example.amtest.adapter.JindiaoRvAdapter;

import java.util.Arrays;

import butterknife.BindArray;
import butterknife.BindView;

public class JindiaoFragment extends BaseFragment {

    @BindView(R.id.jindiao_recycler_view)
    RecyclerView recyclerView;

    @BindArray(R.array.jindiao_array)
    String[] textDatas;

    private JindiaoRvAdapter mAdapter;

    public static JindiaoFragment newInstance(){
        return new JindiaoFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jindiao;
    }

    @Override
    protected void initView(LayoutInflater layoutInflater, Bundle savedInstanceState, ViewGroup container) {
        if(mAdapter == null){
            mAdapter = new JindiaoRvAdapter(Arrays.asList(textDatas));
            mAdapter.addFooterView(layoutInflater.inflate(R.layout.footer_view_jindiao_rv, container, false));
            mAdapter.setFooterViewAsFlow(true);
            recyclerView.setLayoutManager(new GridLayoutManager(fatherActivity, 3));
            recyclerView.setAdapter(mAdapter);
        }
    }
}
