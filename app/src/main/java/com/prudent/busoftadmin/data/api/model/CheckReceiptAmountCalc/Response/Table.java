
package com.prudent.busoftadmin.data.api.model.CheckReceiptAmountCalc.Response;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Table {

    @SerializedName("column1")
    private Double mColumn1;
    @SerializedName("column2")
    private Double mColumn2;

    public Double getColumn1() {
        return mColumn1;
    }

    public void setColumn1(Double column1) {
        mColumn1 = column1;
    }

    public Double getColumn2() {
        return mColumn2;
    }

    public void setColumn2(Double column2) {
        mColumn2 = column2;
    }

}
