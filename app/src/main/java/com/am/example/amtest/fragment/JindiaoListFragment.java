package com.am.example.amtest.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.am.example.amtest.MyApplication;
import com.am.example.amtest.R;
import com.am.example.amtest.adapter.JindiaoListRvAdapter;
import com.am.example.amtest.utils.SpfUtils;
import com.am.example.amtest.widget.MyRvDivider;

import java.util.Arrays;
import java.util.Set;

import butterknife.BindArray;
import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class JindiaoListFragment extends BaseFragment {

    private static final String CHECK_BOX_STATUS = "checkBox_status";

    @BindArray(R.array.jindiao_list)
    String[] list;

    @BindView(R.id.jindiao_list_rv)
    RecyclerView recyclerView;

    private JindiaoListRvAdapter mAdapter;

    public static JindiaoListFragment newInstance(){
        return new JindiaoListFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jindiao_list;
    }

    @Override
    protected void initView(LayoutInflater layoutInflater, Bundle savedInstanceState, ViewGroup container) {
        if(mAdapter == null){
            mAdapter = new JindiaoListRvAdapter(Arrays.asList(list));
            recyclerView.setLayoutManager(new LinearLayoutManager(fatherActivity));
            recyclerView.addItemDecoration(new MyRvDivider(fatherActivity));
            recyclerView.setAdapter(mAdapter);
        }
        Set<String> set = SpfUtils.get().getStringSet(MyApplication.getAppContext(), CHECK_BOX_STATUS);
        if(set != null) {
            mAdapter.setStringSet(set);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mAdapter != null && mAdapter.getStringSet() != null){
            Observable.create(new ObservableOnSubscribe<Boolean>() {
                @Override
                public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                    Set<String> stringSet = mAdapter.getStringSet();
                    Log.d("jindiao", "set: "+stringSet.size());
                    SpfUtils.get().saveStringSet(MyApplication.getAppContext(), CHECK_BOX_STATUS, stringSet);
                }
            }).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe();
        }
    }
}
