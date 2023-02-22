package com.david.software2.models;


/**
 * Appointment month class for appointment count by month
 * Looking back this would have been easier to do with sql
 */
public class AppointmentMonth  {
    private String month;
    private int count;

    public AppointmentMonth(String month, int count) {
        this.month = month;
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
}
