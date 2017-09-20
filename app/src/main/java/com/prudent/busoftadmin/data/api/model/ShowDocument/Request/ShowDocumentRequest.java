package com.prudent.busoftadmin.data.api.model.ShowDocument.Request;

/**
 * Created by Dharmik Patel on 24-May-17.
 */

public class ShowDocumentRequest {

    private String formname;
    private String userid;
    private String corpcentre;
    private String type;
    private String para1;
    private String para2;

    public ShowDocumentRequest(String formname, String userid, String corpcentre, String type, String para1, String para2) {
        this.formname = formname;
        this.userid = userid;
        this.corpcentre = corpcentre;
        this.type = type;
        this.para1 = para1;
        this.para2 = para2;
    }

    public String getFormname() {
        return formname;
    }

    public void setFormname(String formname) {
        this.formname = formname;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPara1() {
        return para1;
    }

    public void setPara1(String para1) {
        this.para1 = para1;
    }

    public String getPara2() {
        return para2;
    }

    public void setPara2(String para2) {
        this.para2 = para2;
    }

}
