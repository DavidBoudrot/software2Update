package com.david.software2.models;


/**
 * I realized a requirement for the reports table was to have a report with both the month type and count.
 * My original plan was to have the tables be seperate but this worked as a quick fix.
 *
 */
public class AppointmentCountMonthType  {
    private String month;
    private int count;

    private String type;






    public AppointmentCountMonthType(String month, int count, String type) {
        this.month = month;
        this.count = count;
        this.type = type;
    }

    public AppointmentCountMonthType(String month, String type, int count) {
        this.month = month;
        this.type = type;
        this.count = count;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setAppointmentCount(int i) {
        this.count = i;
    }

    public void setType(String type) {
        this.type = type;
    }
}
