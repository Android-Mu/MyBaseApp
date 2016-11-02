package com.mjj.baseapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 本地缓存（xml）工具类
 */

public class SharesUtils {

    private Context ctx;
    public String name = "carstation";
    public String UserId;
    public String ProvinceId;
    public String CityId;
    private String IsParkingViod;
    private String IsPlaceVido;
    public String cheId;
    private SharedPreferences share;
    private SharedPreferences setting;

    public SharesUtils(Context ctx) {
        this.ctx = ctx;
        share = ctx.getSharedPreferences(name, 0);
        setting = ctx.getSharedPreferences("setting", 0);
    }

    public void writeXML(String key, String value) {
//        SharedPreferences share = ctx.getSharedPreferences(name, 0);
        Editor edt = share.edit();
        edt.putString(key, value);
        edt.commit();
    }

    public String readXML(String key) {
//        SharedPreferences share = ctx.getSharedPreferences(name, 0);
        String result = share.getString(key, "");
        return result;
    }

    public void putBoolean(String key, boolean value) {
//        SharedPreferences setting = ctx.getSharedPreferences("setting", 0);
        setting.edit().putBoolean(key, false).commit();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
//        SharedPreferences setting = ctx.getSharedPreferences("setting", 0);
        boolean result = setting.getBoolean(key,defaultValue);
        return result;
    }

    public void CleadXml() {
        SharedPreferences share = ctx.getSharedPreferences(name, 0);
        Editor edt = share.edit();
        edt.putString("id", "");
        edt.commit();
    }

    public void clearSaveDate(){
        share.edit().remove(name).commit();
        share.edit().clear().commit();
//        setting.edit().remove("setting").commit();
//        setting.edit().clear().commit();
    }
}
