
package com.prudent.busoftadmin.data.api.model.CheckReceiptBase.Response;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Table {

    @SerializedName("master")
    private String mMaster;
    @SerializedName("xcode")
    private String mXcode;
    @SerializedName("xname")
    private String mXname;

    public String getMaster() {
        return mMaster;
    }

    public void setMaster(String master) {
        mMaster = master;
    }

    public String getXcode() {
        return mXcode;
    }

    public void setXcode(String xcode) {
        mXcode = xcode;
    }

    public String getXname() {
        return mXname;
    }

    public void setXname(String xname) {
        mXname = xname;
    }

}
