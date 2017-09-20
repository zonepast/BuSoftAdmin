package com.prudent.busoftadmin.data.api.model.TranscationUploadDocument.Request;

/**
 * Created by Dharmik Patel on 23-May-17.
 */

public class TranscationUploadDocumentRequest {

    private String formname;
    private String userid;
    private String corpcentre;
    private String type;

    public TranscationUploadDocumentRequest(String formname, String userid, String corpcentre, String type) {
        this.formname = formname;
        this.userid = userid;
        this.corpcentre = corpcentre;
        this.type = type;
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

}
