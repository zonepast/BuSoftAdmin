
package com.prudent.busoftadmin.data.api.model.Calender.Response;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Table {

    @SerializedName("color")
    private String mColor;
    @SerializedName("endTime")
    private String mEndTime;
    @SerializedName("fromDate")
    private String mFromDate;
    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("startTime")
    private String mStartTime;
    @SerializedName("toDate")
    private String mToDate;

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String endTime) {
        mEndTime = endTime;
    }

    public String getFromDate() {
        return mFromDate;
    }

    public void setFromDate(String fromDate) {
        mFromDate = fromDate;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String startTime) {
        mStartTime = startTime;
    }

    public String getToDate() {
        return mToDate;
    }

    public void setToDate(String toDate) {
        mToDate = toDate;
    }

}
