package com.am.example.amtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.am.example.amtest.R;
import com.am.example.amtest.activity.SingleFragmentActivity;
import com.am.example.amtest.entity.MyMultiEntity;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MyRvAdapter extends BaseMultiItemQuickAdapter<MyMultiEntity, BaseViewHolder> {

    private Context context;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MyRvAdapter(Context context, List<MyMultiEntity> data) {
        super(data);
        this.context = context;
        addItemType(MyMultiEntity.TYPE_ITEM, R.layout.item_my_content);
        addItemType(MyMultiEntity.TYPE_DIVIDER, R.layout.item_my_divider);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MyMultiEntity item) {
        switch (item.getItemType()){
            case MyMultiEntity.TYPE_ITEM:
                helper.setText(R.id.my_content_text, item.getText())
                        .setImageResource(R.id.my_content_image, MapHolder.getHolder().getValue(item.getText()));
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (helper.getAdapterPosition() - getHeaderLayoutCount()){
                            case 3:
                                Intent intent = new Intent(context, SingleFragmentActivity.class);
                                intent.putExtra(SingleFragmentActivity.FRAGMENT_START, SingleFragmentActivity.JINDIAO_LIST);
                                context.startActivity(intent);
                                break;
                        }
                    }
                });
                break;
        }
    }
}
