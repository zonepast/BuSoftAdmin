package com.prudent.busoftadmin.data.api.model.DashboardData.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table4 {

    @SerializedName("sr")
    @Expose
    private Integer sr;
    @SerializedName("xcode")
    @Expose
    private String xcode;
    @SerializedName("xname")
    @Expose
    private String xname;
    @SerializedName("class")
    @Expose
    private String _class;

    public Integer getSr() {
        return sr;
    }

    public void setSr(Integer sr) {
        this.sr = sr;
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

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

}