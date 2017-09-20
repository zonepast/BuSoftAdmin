package com.prudent.busoftadmin.data.api.model.DetailUploadDocument.Request;

/**
 * Created by Dharmik Patel on 23-May-17.
 */

public class DetailUploadDocumentRequest {

    private String formname;
    private String control;
    private String userid;
    private String corpcentre;
    private String para1;
    private String type;

    public DetailUploadDocumentRequest(String formname, String control, String userid, String corpcentre, String para1, String type) {
        this.formname = formname;
        this.control = control;
        this.userid = userid;
        this.corpcentre = corpcentre;
        this.para1 = para1;
        this.type = type;
    }

    public String getFormname() {
        return formname;
    }

    public void setFormname(String formname) {
        this.formname = formname;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCorpcentre() {
        return corpcentre;
    }

    public void setCorpcentre(String corpcentre) {
        this.corpcentre = corpcentre;
    }

    public String getPara1() {
        return para1;
    }

    public void setPara1(String para1) {
        this.para1 = para1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
