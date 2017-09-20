package com.prudent.busoftadmin.data.db.Local.Login;

import io.realm.RealmObject;

/**
 * Created by Dharmik Patel on 22-May-17.
 */

public class LoginRealm extends RealmObject {

    private String xmaster;
    private String xcode;
    private String xname;

    public LoginRealm() {
    }

    public LoginRealm(String xmaster, String xcode, String xname) {
        this.xmaster = xmaster;
        this.xcode = xcode;
        this.xname = xname;
    }

    public String getXmaster() {
        return xmaster;
    }

    public void setXmaster(String xmaster) {
        this.xmaster = xmaster;
    }

    public String getXcode() {
        return xcode;
    }

    public void setXcode(String xcode) {
        this.xcode = xcode;
    }

    public String getXname() {
        return xname;
    }

    public void setXname(String xname) {
        this.xname = xname;
    }

}
