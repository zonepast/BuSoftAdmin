package com.prudent.busoftadmin.data.pref;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.prudent.busoftadmin.ui.Dashboard.DashboardActivity;
import com.prudent.busoftadmin.ui.Login.LoginActivity;

public class SessionManager {

    private SharedPreferences pref;
    private Editor editor;
    private Context _context;

    private static final String PREF_NAME = "FMS";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_CORPCENTRE_NAME = "corpcentrename";
    public static final String KEY_CORPCENTRE_CODE = "corpcentrecode";
    public static final String KEY_BRANCH_NAME = "branchname";
    public static final String KEY_BRANCH_CODE = "branchcode";
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_USER_CODE = "usercode";

    public SessionManager(Context context){
        this._context = context;
        int PRIVATE_MODE = 0;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String corpname, String corpcode,String username, String usercode){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_CORPCENTRE_NAME, corpname);
        editor.putString(KEY_CORPCENTRE_CODE, corpcode);
        editor.putString(KEY_USER_NAME, username);
        editor.putString(KEY_USER_CODE, usercode);
        editor.commit();
    }

    public void SaveBranch(String branchname, String branchcode){
        editor.putString(KEY_BRANCH_NAME, branchname);
        editor.putString(KEY_BRANCH_CODE, branchcode);
        editor.commit();
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, DashboardActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }

    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_CORPCENTRE_NAME, pref.getString(KEY_CORPCENTRE_NAME, null));
        user.put(KEY_CORPCENTRE_CODE, pref.getString(KEY_CORPCENTRE_CODE, null));
        user.put(KEY_BRANCH_NAME, pref.getString(KEY_BRANCH_NAME, null));
        user.put(KEY_BRANCH_CODE, pref.getString(KEY_BRANCH_CODE, null));
        user.put(KEY_USER_NAME, pref.getString(KEY_USER_NAME, null));
        user.put(KEY_USER_CODE, pref.getString(KEY_USER_CODE, null));
        return user;
    }

    public String getUserCode(){
        return pref.getString(KEY_USER_CODE, null);
    }

    public String getUnitCode(){
        return pref.getString(KEY_BRANCH_CODE, null);
    }

    public String getCorpCode(){
        return pref.getString(KEY_CORPCENTRE_CODE, null);
    }

    public String getUserName(){
        return pref.getString(KEY_USER_NAME, null);
    }

    public String getCorpName(){
        return pref.getString(KEY_CORPCENTRE_NAME, null);
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
