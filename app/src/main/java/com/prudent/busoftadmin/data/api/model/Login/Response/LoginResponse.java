
package com.prudent.busoftadmin.data.api.model.Login.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginResponse {

    private List<Table> table = null;
    private List<Table1> table1 = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }

    public List<Table1> getTable1() {
        return table1;
    }

    public void setTable1(List<Table1> table1) {
        this.table1 = table1;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
