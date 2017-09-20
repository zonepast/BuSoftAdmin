
package com.prudent.busoftadmin.data.api.model.ReportDetail.Request;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ReportDetailRequest {

    public ReportDetailRequest(String mCorpcentre, String mField1, String mReportname, String mUserId) {
        this.mCorpcentre = mCorpcentre;
        this.mField1 = mField1;
        this.mReportname = mReportname;
        this.mUserId = mUserId;
    }

    @SerializedName("corpcentre")
    private String mCorpcentre;
    @SerializedName("field1")
    private String mField1;
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

    public String getField1() {
        return mField1;
    }

    public void setField1(String field1) {
        mField1 = field1;
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
