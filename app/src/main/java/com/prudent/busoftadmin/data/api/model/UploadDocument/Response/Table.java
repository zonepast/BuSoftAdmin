
package com.prudent.busoftadmin.data.api.model.UploadDocument.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("messsage")
    @Expose
    private String messsage;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

}
