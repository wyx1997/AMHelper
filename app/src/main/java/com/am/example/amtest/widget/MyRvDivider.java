package com.am.example.amtest.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.am.example.amtest.R;
import com.am.example.amtest.utils.DensityUtil;

public class MyRvDivider extends RecyclerView.ItemDecoration {

    private int mDividerHeight;

    private int mTopOffset;

    private Paint mDividerPaint;

    private Context mContext;

    public MyRvDivider(Context context) {
        mContext = context;
        mTopOffset = DensityUtil.dip2px(context, 30);
        mDividerHeight = DensityUtil.dip2px(context, 2);

        mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDividerPaint.setStyle(Paint.Style.FILL);
        mDividerPaint.setColor(context.getResources().getColor(R.color.main_bottom_navi_bg));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int index = parent.getChildAdapterPosition(view);
        if(index%2==0 && index!=0){
            outRect.top = mTopOffset/2;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();
        for(int i=0; i<childCount; i++){
            if(i%2==1 && i!=childCount-1){
                View child = parent.getChildAt(i);
                int top = child.getBottom() + mTopOffset/2 - mDividerHeight/2;
                int left = parent.getPaddingLeft();
                int right = parent.getRight() - parent.getPaddingRight();
                int bottom = child.getBottom() + mTopOffset/2 + mDividerHeight/2;
                c.drawRect(left, top, right, bottom, mDividerPaint);
            }
        }
    }
}
