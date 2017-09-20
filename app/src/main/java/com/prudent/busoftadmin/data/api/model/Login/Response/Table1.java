
package com.prudent.busoftadmin.data.api.model.Login.Response;

import java.util.HashMap;
import java.util.Map;

public class Table1 {

    private String xmaster;
    private String xcode;
    private String xname;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getXmaster() {
        return xmaster;
    }

    public void setXmaster(String xmaster) {
        this.xmaster = xmaster;
    }

    public String getXcode() {
        return xcode;
    }

    public void setXcode(String xcode) {
        this.xcode = xcode;
    }

    public String getXname() {
        return xname;
    }

    public void setXname(String xname) {
        this.xname = xname;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
