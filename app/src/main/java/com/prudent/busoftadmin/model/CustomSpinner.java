package com.prudent.busoftadmin.model;

/**
 * Created by Dharmik Patel on 14-Jul-17.
 */

public class CustomSpinner {

    public CustomSpinner(String mXcode, String mXname) {
        this.mXcode = mXcode;
        this.mXname = mXname;
    }

    private String mXcode;
    private String mXname;

    public String getmXcode() {
        return mXcode;
    }

    public CustomSpinner setmXcode(String mXcode) {
        this.mXcode = mXcode;
        return this;
    }

    public String getmXname() {
        return mXname;
    }

    public CustomSpinner setmXname(String mXname) {
        this.mXname = mXname;
        return this;
    }

}
