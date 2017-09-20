package com.prudent.busoftadmin.data;

/**
 * Created by Dharmik Patel on 06-Jun-17.
 */

public class CalenderDate{
    public CalenderDate(String yearId, String yearName) {
        this.yearId = yearId;
        this.yearName = yearName;
    }

    public String getYearId() {
        return yearId;
    }

    public CalenderDate setYearId(String yearId) {
        this.yearId = yearId;
        return this;
    }

    public String getYearName() {
        return yearName;
    }

    public CalenderDate setYearName(String yearName) {
        this.yearName = yearName;
        return this;
    }

    String yearId,yearName;
}
