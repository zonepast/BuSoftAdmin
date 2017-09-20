
package com.prudent.busoftadmin.data.api.model.DashboardData.Response;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Table1 {

    @SerializedName("a")
    private Double mA;
    @SerializedName("x")
    private String mX;
    @SerializedName("y")
    private Double mY;
    @SerializedName("z")
    private Double mZ;

    public Double getA() {
        return mA;
    }

    public void setA(Double a) {
        mA = a;
    }

    public String getX() {
        return mX;
    }

    public void setX(String x) {
        mX = x;
    }

    public Double getY() {
        return mY;
    }

    public void setY(Double y) {
        mY = y;
    }

    public Double getZ() {
        return mZ;
    }

    public void setZ(Double z) {
        mZ = z;
    }

}
