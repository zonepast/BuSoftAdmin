
package com.prudent.busoftadmin.data.api.model.DashboardData.Response;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Table3 {

    @SerializedName("actype")
    private String mActype;
    @SerializedName("amount")
    private String mAmount;
    @SerializedName("color")
    private String mColor;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("lable")
    private String mLable;
    @SerializedName("pCColor")
    private String mPCColor;
    @SerializedName("pc")
    private Long mPc;
    @SerializedName("status")
    private String mStatus;

    public String getActype() {
        return mActype;
    }

    public void setActype(String actype) {
        mActype = actype;
    }

    public String getAmount() {
        return mAmount;
    }

    public void setAmount(String amount) {
        mAmount = amount;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getLable() {
        return mLable;
    }

    public void setLable(String lable) {
        mLable = lable;
    }

    public String getPCColor() {
        return mPCColor;
    }

    public void setPCColor(String pCColor) {
        mPCColor = pCColor;
    }

    public Long getPc() {
        return mPc;
    }

    public void setPc(Long pc) {
        mPc = pc;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
