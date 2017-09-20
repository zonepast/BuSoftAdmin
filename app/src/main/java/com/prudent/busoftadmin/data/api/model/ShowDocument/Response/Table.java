
package com.prudent.busoftadmin.data.api.model.ShowDocument.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

    @SerializedName("doc_Value")
    @Expose
    private String docValue;
    @SerializedName("doc_name")
    @Expose
    private String docName;
    @SerializedName("doc_Ext")
    @Expose
    private String docExt;

    public String getDocValue() {
        return docValue;
    }

    public void setDocValue(String docValue) {
        this.docValue = docValue;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocExt() {
        return docExt;
    }

    public void setDocExt(String docExt) {
        this.docExt = docExt;
    }

}
