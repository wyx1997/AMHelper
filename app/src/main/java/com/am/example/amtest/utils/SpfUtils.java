package com.am.example.amtest.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class SpfUtils {

    private static SpfUtils sInstance = new SpfUtils();

    private SpfUtils(){}

    public static SpfUtils get(){
        return sInstance;
    }

    public void saveSingleValue(Context context, String key, Object value){
        SharedPreferences spf = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        if(value instanceof Integer){
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        }
        editor.apply();
    }

    public void saveStringSet(Context context, String key, Set<String> set){
        SharedPreferences spf = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putStringSet(key, set);
        editor.apply();
    }

    public Object getSingleValue(Context context, String key, Object defaultObject){
        SharedPreferences sp = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        Object returnValue = null;
        if (defaultObject instanceof String) {
            returnValue = sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            returnValue = sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            returnValue = sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            returnValue = sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            returnValue = sp.getLong(key, (Long) defaultObject);
        }
        return returnValue;
    }

    public Set<String> getStringSet(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return sp.getStringSet(key, null);
    }
}
