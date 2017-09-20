
package com.prudent.busoftadmin.data.api.model.DetailUploadDocument.Response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailUploadDocumentResponse {

    @SerializedName("table")
    @Expose
    private List<Table> table = null;

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }

}
