package com.alhonof.app;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    SharedPreferences sharedpreferences;
    public  String share = "privateFile";
    public  void saveToShared(String key, String val){
       sharedpreferences.edit().putString(key,val).apply();
    }


    public  String getString(String key){
        return  sharedpreferences.getString(key,"");
    }

    SharedPref(Context context){
        this.sharedpreferences = context.getSharedPreferences(this.share, Context.MODE_PRIVATE);

    }
}
