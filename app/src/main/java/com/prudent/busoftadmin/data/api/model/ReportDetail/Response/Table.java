
package com.prudent.busoftadmin.data.api.model.ReportDetail.Response;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Table {

    @SerializedName("account")
    private String mAccount;
    @SerializedName("change")
    private String mChange;
    @SerializedName("closing")
    private Double mClosing;
    @SerializedName("credit")
    private Double mCredit;
    @SerializedName("debit")
    private Double mDebit;
    @SerializedName("opening")
    private Double mOpening;

    public String getAccount() {
        return mAccount;
    }

    public void setAccount(String account) {
        mAccount = account;
    }

    public String getChange() {
        return mChange;
    }

    public void setChange(String change) {
        mChange = change;
    }

    public Double getClosing() {
        return mClosing;
    }

    public void setClosing(Double closing) {
        mClosing = closing;
    }

    public Double getCredit() {
        return mCredit;
    }

    public void setCredit(Double credit) {
        mCredit = credit;
    }

    public Double getDebit() {
        return mDebit;
    }

    public void setDebit(Double debit) {
        mDebit = debit;
    }

    public Double getOpening() {
        return mOpening;
    }

    public void setOpening(Double opening) {
        mOpening = opening;
    }

}
