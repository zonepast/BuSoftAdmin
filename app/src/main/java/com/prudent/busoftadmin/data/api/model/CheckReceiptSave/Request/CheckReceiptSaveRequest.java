package com.prudent.busoftadmin.data.api.model.CheckReceiptSave.Request;

/**
 * Created by Dharmik Patel on 15-Jul-17.
 */

public class CheckReceiptSaveRequest {

    private String srno;
    private String date;
    private String party;
    private String rate;
    private String chqno;
    private String bankname;
    private String chqdate;
    private String total;
    private String amount;
    private String interest;
    private String netamt;
    private String adjamt;
    private String payable;
    private String userid;
    private String entrydatetime;
    private String editedby;
    private String editdatetime;
    private String corpcentre;
    private String unitCorp;
    private String terminal;

    public CheckReceiptSaveRequest(String srno, String date, String party, String rate, String chqno, String bankname, String chqdate, String total, String amount, String interest, String netamt, String adjamt, String payable, String userid, String entrydatetime, String editedby, String editdatetime, String corpcentre, String unitCorp, String terminal) {
        this.srno = srno;
        this.date = date;
        this.party = party;
        this.rate = rate;
        this.chqno = chqno;
        this.bankname = bankname;
        this.chqdate = chqdate;
        this.total = total;
        this.amount = amount;
        this.interest = interest;
        this.netamt = netamt;
        this.adjamt = adjamt;
        this.payable = payable;
        this.userid = userid;
        this.entrydatetime = entrydatetime;
        this.editedby = editedby;
        this.editdatetime = editdatetime;
        this.corpcentre = corpcentre;
        this.unitCorp = unitCorp;
        this.terminal = terminal;
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

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getChqno() {
        return chqno;
    }

    public void setChqno(String chqno) {
        this.chqno = chqno;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getChqdate() {
        return chqdate;
    }

    public void setChqdate(String chqdate) {
        this.chqdate = chqdate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getNetamt() {
        return netamt;
    }

    public void setNetamt(String netamt) {
        this.netamt = netamt;
    }

    public String getAdjamt() {
        return adjamt;
    }

    public void setAdjamt(String adjamt) {
        this.adjamt = adjamt;
    }

    public String getPayable() {
        return payable;
    }

    public void setPayable(String payable) {
        this.payable = payable;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEntrydatetime() {
        return entrydatetime;
    }

    public void setEntrydatetime(String entrydatetime) {
        this.entrydatetime = entrydatetime;
    }

    public String getEditedby() {
        return editedby;
    }

    public void setEditedby(String editedby) {
        this.editedby = editedby;
    }

    public String getEditdatetime() {
        return editdatetime;
    }

    public void setEditdatetime(String editdatetime) {
        this.editdatetime = editdatetime;
    }

    public String getCorpcentre() {
        return corpcentre;
    }

    public void setCorpcentre(String corpcentre) {
        this.corpcentre = corpcentre;
    }

    public String getUnitCorp() {
        return unitCorp;
    }

    public void setUnitCorp(String unitCorp) {
        this.unitCorp = unitCorp;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
}
