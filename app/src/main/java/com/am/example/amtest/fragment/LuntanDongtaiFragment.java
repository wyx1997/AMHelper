package com.am.example.amtest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.am.example.amtest.R;
import com.am.example.amtest.adapter.LuntanDongtaiRvAdapter;
import com.am.example.amtest.entity.LuntanDongtaiRvEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LuntanDongtaiFragment extends BaseFragment {

    @BindView(R.id.luntan_view_pager_dongtai)
    RecyclerView recyclerView;

    private Context context;

    private LuntanDongtaiRvAdapter mAdapter;

    public static LuntanDongtaiFragment newInstance(Context context){
        LuntanDongtaiFragment fragment = new LuntanDongtaiFragment();
        fragment.context = context;
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_luntan_dongtai;
    }

    @Override
    protected void initView(LayoutInflater layoutInflater, Bundle savedInstanceState, ViewGroup container) {
        List<LuntanDongtaiRvEntity> list = new ArrayList<>();
        list.add(new LuntanDongtaiRvEntity("nihao", "test", "123", "321"));
        list.add(new LuntanDongtaiRvEntity("nihao", "test", "123", "321"));
        if(mAdapter == null){
            mAdapter = new LuntanDongtaiRvAdapter(list);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(mAdapter);
        }
    }
}
