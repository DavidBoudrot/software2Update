package com.david.software2.models;

import java.time.LocalDateTime;

public class Division {

    private int divisionID;
    private String divisionName;
    private int countryID;
    private String createdBy;
    private String lastUpdatedBy;
    private String createDate;
    private LocalDateTime lastUpdate;


    public Division(int divisionID, String divisionName, int countryID, String createdBy, String lastUpdatedBy, String createDate, LocalDateTime lastUpdate) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void print() {
        System.out.println("Division ID: " + divisionID);
        System.out.println("Division Name: " + divisionName);
        System.out.println("Country ID: " + countryID);
        System.out.println("Created By: " + createdBy);
        System.out.println("Last Updated By: " + lastUpdatedBy);
        System.out.println("Create Date: " + createDate);
        System.out.println("Last Update: " + lastUpdate);
    }


}