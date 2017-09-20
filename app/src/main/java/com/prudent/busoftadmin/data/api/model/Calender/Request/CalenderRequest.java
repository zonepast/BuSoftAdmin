
package com.prudent.busoftadmin.data.api.model.Calender.Request;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CalenderRequest {

    public CalenderRequest(String mCompanyId, String mDate, String mIp, String mUserId) {
        this.mCompanyId = mCompanyId;
        this.mDate = mDate;
        this.mIp = mIp;
        this.mUserId = mUserId;
    }

    @SerializedName("companyId")
    private String mCompanyId;
    @SerializedName("date")
    private String mDate;
    @SerializedName("ip")
    private String mIp;
    @SerializedName("userId")
    private String mUserId;

    public String getCompanyId() {
        return mCompanyId;
    }

    public void setCompanyId(String companyId) {
        mCompanyId = companyId;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getIp() {
        return mIp;
    }

    public void setIp(String ip) {
        mIp = ip;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

}
