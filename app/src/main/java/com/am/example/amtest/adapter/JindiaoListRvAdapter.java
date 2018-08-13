package com.am.example.amtest.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;

import com.am.example.amtest.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class JindiaoListRvAdapter extends BaseQuickAdapter<String, BaseViewHolder> implements BaseQuickAdapter.OnItemChildClickListener{

    private Set<String> stringSet;

    public JindiaoListRvAdapter(@Nullable List<String> data) {
        super(R.layout.item_jindiao_list, data);
        setOnItemChildClickListener(this);
        stringSet = new LinkedHashSet<>();
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.jindiao_list_item_number, handleNumber(helper.getAdapterPosition()))
                .setText(R.id.jindiao_list_item_content, item)
                .setChecked(R.id.jindiao_list_item_checkbox, stringSet.contains(item))
                .addOnClickListener(R.id.jindiao_list_item_checkbox);
    }

    private String handleNumber(int index){
        StringBuilder number = new StringBuilder();
        if(++index < 10){
            number.append("0").append(index);
        } else {
            number.append(index);
        }
        return number.toString();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        CheckBox checkBox = (CheckBox) view;
        String value = (String) adapter.getItem(position);
        if(checkBox.isChecked()){
            stringSet.add(value);
        } else if(stringSet.contains(value)){
            stringSet.remove(value);
        }
    }

    public Set<String> getStringSet() {
        return stringSet;
    }

    public void setStringSet(Set<String> stringSet) {
        //spf的getStringSet方法不允许修改返回的实例，此处只能采取赋值的方式
        this.stringSet.clear();
        this.stringSet.addAll(stringSet);
    }
}
