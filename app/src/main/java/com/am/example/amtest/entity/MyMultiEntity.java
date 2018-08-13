package com.am.example.amtest.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MyMultiEntity implements MultiItemEntity {

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_DIVIDER = 1;

    private String text;

    public MyMultiEntity(String text) {
        this.text = text;
    }

    @Override
    public int getItemType() {
        return "分".equals(text) ? TYPE_DIVIDER : TYPE_ITEM;
    }

    public String getText() {
        return text;
    }
}
