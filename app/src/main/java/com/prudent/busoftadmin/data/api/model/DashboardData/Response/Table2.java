
package com.prudent.busoftadmin.data.api.model.DashboardData.Response;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Table2 {

    @SerializedName("actype")
    private String mActype;
    @SerializedName("color")
    private String mColor;
    @SerializedName("performance")
    private Double mPerformance;

    public String getActype() {
        return mActype;
    }

    public void setActype(String actype) {
        mActype = actype;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }

    public Double getPerformance() {
        return mPerformance;
    }

    public void setPerformance(Double performance) {
        mPerformance = performance;
    }

}
