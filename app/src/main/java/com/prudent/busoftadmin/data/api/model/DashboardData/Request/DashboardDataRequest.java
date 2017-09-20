
package com.prudent.busoftadmin.data.api.model.DashboardData.Request;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class DashboardDataRequest {

    public DashboardDataRequest(String mCorpcentre, String mReportname, String mUserId) {
        this.mCorpcentre = mCorpcentre;
        this.mReportname = mReportname;
        this.mUserId = mUserId;
    }

    @SerializedName("corpcentre")
    private String mCorpcentre;
    @SerializedName("reportname")
    private String mReportname;
    @SerializedName("userId")
    private String mUserId;

    public String getCorpcentre() {
        return mCorpcentre;
    }

    public void setCorpcentre(String corpcentre) {
        mCorpcentre = corpcentre;
    }

    public String getReportname() {
        return mReportname;
    }

    public void setReportname(String reportname) {
        mReportname = reportname;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

}
