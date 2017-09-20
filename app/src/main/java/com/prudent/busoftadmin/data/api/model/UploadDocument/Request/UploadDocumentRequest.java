package com.prudent.busoftadmin.data.api.model.UploadDocument.Request;

/**
 * Created by Dharmik Patel on 25-May-17.
 */

public class UploadDocumentRequest {

    private String voucher;
    private String srno;
    private String date;
    private String particular;
    private String doc_Value;
    private String doc_Name;
    private String doc_Ext;
    private String doc_Size;
    private String corpcentre;
    private String userid;
    private String entryDateTime;

    public UploadDocumentRequest(String voucher, String srno, String date, String particular, String docValue, String docName, String docExt, String docSize, String corpcentre, String userid, String entryDateTime) {
        this.voucher = voucher;
        this.srno = srno;
        this.date = date;
        this.particular = particular;
        this.doc_Value = docValue;
        this.doc_Name = docName;
        this.doc_Ext = docExt;
        this.doc_Size = docSize;
        this.corpcentre = corpcentre;
        this.userid = userid;
        this.entryDateTime = entryDateTime;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getSrno() {
        return srno;
    }

    public void setSrno(String srno) {
        this.srno = srno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getParticular() {
        return particular;
    }

    public void setParticular(String particular) {
        this.particular = particular;
    }

    public String getDocValue() {
        return doc_Value;
    }

    public void setDocValue(String docValue) {
        this.doc_Value = docValue;
    }

    public String getDocName() {
        return doc_Name;
    }

    public void setDocName(String docName) {
        this.doc_Name = docName;
    }

    public String getDocExt() {
        return doc_Ext;
    }

    public void setDocExt(String docExt) {
        this.doc_Ext = docExt;
    }

    public String getDocSize() {
        return doc_Size;
    }

    public void setDocSize(String docSize) {
        this.doc_Size = docSize;
    }

    public String getCorpcentre() {
        return corpcentre;
    }

    public void setCorpcentre(String corpcentre) {
        this.corpcentre = corpcentre;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEntryDateTime() {
        return entryDateTime;
    }

    public void setEntryDateTime(String entryDateTime) {
        this.entryDateTime = entryDateTime;
    }

}
