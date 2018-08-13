package com.am.example.amtest.utils;

import android.content.Context;

public class DensityUtil {

    private static float mScale = 1.0f;

    public static int dip2px(Context context, int dpValue){
        if(mScale == 1.0f) {
            mScale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (dpValue * mScale + 0.5f);
    }
}
