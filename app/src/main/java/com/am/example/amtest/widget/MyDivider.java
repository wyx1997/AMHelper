package com.am.example.amtest.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.am.example.amtest.R;
import com.am.example.amtest.utils.DensityUtil;

public class MyDivider extends View {

    private Paint mDividerPaint;

    private int mDividerHeight;
    private int mViewHeight;
    private int mViewWidth;

    public MyDivider(Context context) {
        super(context);
        init(context);
    }

    public MyDivider(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        mDividerHeight = DensityUtil.dip2px(context, 2);

        mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDividerPaint.setColor(context.getResources().getColor(R.color.main_bottom_navi_bg));
        mDividerPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, getTop()+mViewHeight/2 - mDividerHeight/2, mViewWidth,
                getTop()+mViewHeight/2 + mDividerHeight/2,  mDividerPaint);
    }
}
