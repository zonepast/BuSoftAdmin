
package com.prudent.busoftadmin.data.api.model.DashboardData.Response;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class DashboardDataResponse {

    @SerializedName("table")
    private List<Table> mTable;
    @SerializedName("table1")
    private List<Table1> mTable1;
    @SerializedName("table2")
    private List<Table2> mTable2;
    @SerializedName("table3")
    private List<Table3> mTable3;
    @SerializedName("table4")
    private List<Table4> table4;

    public List<Table> getTable() {
        return mTable;
    }

    public void setTable(List<Table> table) {
        mTable = table;
    }

    public List<Table1> getTable1() {
        return mTable1;
    }

    public void setTable1(List<Table1> table1) {
        mTable1 = table1;
    }

    public List<Table2> getTable2() {
        return mTable2;
    }

    public void setTable2(List<Table2> table2) {
        mTable2 = table2;
    }

    public List<Table3> getTable3() {
        return mTable3;
    }

    public void setTable3(List<Table3> table3) {
        mTable3 = table3;
    }

    public List<Table4> getTable4() {
        return table4;
    }

    public void setTable4(List<Table4> table4) {
        this.table4 = table4;
    }
}
