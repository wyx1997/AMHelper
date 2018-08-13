package com.am.example.amtest.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

import com.am.example.amtest.R;

public class MyRadioButton extends AppCompatRadioButton {

    public MyRadioButton(Context context) {
        super(context);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyRadioButton);
        Drawable drawable = typedArray.getDrawable(R.styleable.MyRadioButton_drawableTop);
        if(drawable != null) {
            int left = (int) typedArray.getDimension(R.styleable.MyRadioButton_drawable_left, 0);
            int top = (int) typedArray.getDimension(R.styleable.MyRadioButton_drawable_top, 20);
            int right = (int) typedArray.getDimension(R.styleable.MyRadioButton_drawable_right, 80);
            int bottom = (int) typedArray.getDimension(R.styleable.MyRadioButton_drawable_bottom, 90);
            drawable.setBounds(left, top, right, bottom);
            setCompoundDrawables(null, drawable, null, null);
        }
        typedArray.recycle();
    }
}
