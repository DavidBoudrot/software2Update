package com.david.software2.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The division class for states and provinces.
 */
public class Division {

    private int divisionID;
    private String divisionName;
    private int countryID;
    private String createdBy;
    private String lastUpdatedBy;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;


    /**
     * Constructer for the division class
     */
    public Division(int divisionID, String divisionName, int countryID, String createdBy, String lastUpdatedBy, LocalDateTime createDate, LocalDateTime lastUpdate) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }
    /**
     * Getters and setters
     */
    public int getDivisionID() {
        return divisionID;
    }
    /**
     * Getters and setters
     */
    public String getDivisionName() {
        return divisionName;
    }
    /**
     * Getters and setters
     */
    public int getCountryID() {
        return countryID;
    }
    /**
     * Getters and setters
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * Getters and setters
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * Getters and setters
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    /**
     * Getters and setters
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * Getters and setters
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    /**
     * Getters and setters
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    /**
     * Getters and setters
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}