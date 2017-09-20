
package com.prudent.busoftadmin.data.api.model.Report2Detail.Request;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

public class Report2DetailResponse {

    private String Corpcentre;
    private String Field1;

    public Report2DetailResponse(String mCorpcentre, String mField1, String mReportname, String mUserId) {
        this.Corpcentre = mCorpcentre;
        this.Field1 = mField1;
        this.Reportname = mReportname;
        this.UserId = mUserId;
    }

    private String Reportname;
    private String UserId;

    public String getCorpcentre() {
        return Corpcentre;
    }

    public void setCorpcentre(String corpcentre) {
        Corpcentre = corpcentre;
    }

    public String getField1() {
        return Field1;
    }

    public void setField1(String field1) {
        Field1 = field1;
    }

    public String getReportname() {
        return Reportname;
    }

    public void setReportname(String reportname) {
        Reportname = reportname;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

}
