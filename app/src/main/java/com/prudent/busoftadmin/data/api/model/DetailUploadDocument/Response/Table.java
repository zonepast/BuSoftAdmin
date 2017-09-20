
package com.prudent.busoftadmin.data.api.model.DetailUploadDocument.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

    @SerializedName("xcode")
    @Expose
    private String xcode;
    @SerializedName("xname")
    @Expose
    private String xname;

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
