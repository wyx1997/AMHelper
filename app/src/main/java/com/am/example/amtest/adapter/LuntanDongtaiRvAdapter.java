package com.am.example.amtest.adapter;

import android.support.annotation.Nullable;

import com.am.example.amtest.R;
import com.am.example.amtest.entity.LuntanDongtaiRvEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class LuntanDongtaiRvAdapter extends BaseQuickAdapter<LuntanDongtaiRvEntity, BaseViewHolder> {

    public LuntanDongtaiRvAdapter(@Nullable List<LuntanDongtaiRvEntity> data) {
        super(R.layout.item_luntan_dongtai_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LuntanDongtaiRvEntity item) {
        helper.setText(R.id.luntan_dongtai_title, item.getTitle())
                .setText(R.id.luntan_dongtai_content, item.getContent())
                .setText(R.id.luntan_dongtai_like_number, item.getLikeNum())
                .setText(R.id.luntan_dongtai_comment_number, item.getCommentNum());
    }
}
