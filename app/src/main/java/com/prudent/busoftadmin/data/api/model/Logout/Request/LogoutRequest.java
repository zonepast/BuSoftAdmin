
package com.prudent.busoftadmin.data.api.model.Logout.Request;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class LogoutRequest {

    @SerializedName("control")
    private String mControl;

    public LogoutRequest(String mControl, String mCorporate, String mIp, String mModule, String mPassword, String mUserid, String mUsername) {
        this.mControl = mControl;
        this.mCorporate = mCorporate;
        this.mIp = mIp;
        this.mModule = mModule;
        this.mPassword = mPassword;
        this.mUserid = mUserid;
        this.mUsername = mUsername;
    }

    @SerializedName("corporate")
    private String mCorporate;
    @SerializedName("ip")
    private String mIp;
    @SerializedName("module")
    private String mModule;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("userid")
    private String mUserid;
    @SerializedName("username")
    private String mUsername;

    public String getControl() {
        return mControl;
    }

    public void setControl(String control) {
        mControl = control;
    }

    public String getCorporate() {
        return mCorporate;
    }

    public void setCorporate(String corporate) {
        mCorporate = corporate;
    }

    public String getIp() {
        return mIp;
    }

    public void setIp(String ip) {
        mIp = ip;
    }

    public String getModule() {
        return mModule;
    }

    public void setModule(String module) {
        mModule = module;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getUserid() {
        return mUserid;
    }

    public void setUserid(String userid) {
        mUserid = userid;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

}
