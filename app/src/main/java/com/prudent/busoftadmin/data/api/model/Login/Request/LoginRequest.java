package com.prudent.busoftadmin.data.api.model.Login.Request;

/**
 * Created by Dharmik Patel on 22-May-17.
 */

public class LoginRequest {

    private String module;
    private String control;
    private String corporate;
    private String userid;
    private String ip;
    private String username;
    private String password;

    public LoginRequest(String module, String control, String corporate, String userid, String ip, String username, String password) {
        this.module = module;
        this.control = control;
        this.corporate = corporate;
        this.userid = userid;
        this.ip = ip;
        this.username = username;
        this.password = password;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
