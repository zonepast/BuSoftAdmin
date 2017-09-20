package com.prudent.busoftadmin.data.api.model.CheckReceiptBase.Request;

/**
 * Created by Dharmik Patel on 13-Jul-17.
 */

public class ChequeReceiptBaseRequest {

    private String userid;
    private String corpcentre;
    private String type;

    public ChequeReceiptBaseRequest(String userid, String corpcentre, String type) {
        this.userid = userid;
        this.corpcentre = corpcentre;
        this.type = type;
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
