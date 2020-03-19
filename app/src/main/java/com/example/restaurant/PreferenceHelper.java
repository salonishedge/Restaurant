package com.example.restaurant;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private final String INTRO = "intro";
    private final String USERNAME = "username";
    private final String CUISINE = "cuisine";
    private final String RESTAURANT = "restaurant";
    private final String OCASSION = "ocassion";
    private final String USERID = "userid";


    private SharedPreferences app_prefs;
    private Context context;

    public PreferenceHelper(Context context) {
        app_prefs = context.getSharedPreferences("shared",
                Context.MODE_PRIVATE);
        this.context = context;
    }

    public void putIsLogin(boolean loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(INTRO, loginorout);
        edit.commit();
    }

    public boolean getIsLogin() {
        return app_prefs.getBoolean(INTRO, false);
    }

    public void putUSERNAME(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(USERNAME, loginorout);
        edit.commit();

    }
    public String getUSERNAME() {
        return app_prefs.getString(USERNAME, "");
    }

    public void putCUISINE(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(CUISINE, loginorout);
        edit.commit();
    }
    public String getCUISINE() {
        return app_prefs.getString(CUISINE, "");
    }

    public void putRESTAURANT(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(RESTAURANT, loginorout);
        edit.commit();
    }
    public String getRESTAURANT() {
        return app_prefs.getString(RESTAURANT, "");
    }

    public void putOCASSION(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(OCASSION, loginorout);
        edit.commit();
    }
    public String getOCASSION() {
        return app_prefs.getString(OCASSION, "");
    }




    public void putUSERID(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(USERID, loginorout);
        edit.commit();
    }

    public String getUSERID() {
        return app_prefs.getString(USERID, "");
    }
}
