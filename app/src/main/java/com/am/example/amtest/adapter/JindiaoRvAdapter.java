package com.am.example.amtest.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.am.example.amtest.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JindiaoRvAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public JindiaoRvAdapter(@Nullable List<String> data) {
        super(R.layout.item_jindiao_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setImageResource(R.id.item_jindiao_image, MapHolder.getHolder().getValue(item))
                .setText(R.id.item_jindiao_text, item);
    }
}
