
package com.prudent.busoftadmin.data.api.model.CheckReceiptSave.Response;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Table {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("success")
    private Long mSuccess;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Long getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Long success) {
        mSuccess = success;
    }

}
