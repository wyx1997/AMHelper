package com.am.example.amtest.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;
import android.widget.Toast;

public class ToastUtils {

    private static Toast mToast;
    private static long lastTime;
    private static String lastText;

    @SuppressLint("ShowToast")
    public static void showToast(Context context, String text){
        long time = SystemClock.currentThreadTimeMillis();
        if(null != mToast){
            if((time - lastTime) < Toast.LENGTH_SHORT && lastText.equals(text)) return;
        } else {
            mToast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
        }
        mToast.setText(text);
        lastText = text;
        lastTime = time;
        mToast.show();
    }

    public static void showToast(Context context, int resId){
        showToast(context, context.getResources().getString(resId));
    }
}
