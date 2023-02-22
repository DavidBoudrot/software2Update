package com.david.software2.models;

/**
 * The country class for the country table in the database.
 */

import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Country {
    private int countryID;
    private String countryName;

    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private LocalDateTime createDate;
    private String createdBy;


    /**
     * Constructer for the country class
     */


    public Country(int countryID, String countryName, LocalDateTime lastUpdate, String lastUpdatedBy, LocalDateTime createDate, String createdBy) {
        this.countryID = countryID;
        this.countryName = countryName;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.createDate = createDate;
        this.createdBy = createdBy;
    }

    public Country() {

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
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
    /**
     * Getters and setters
     */
    public String getCountryName() {
        return countryName;
    }
    /**
     * Getters and setters
     */
    public void setCountryName(String countryName) {this.countryName = countryName; }
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
    public LocalDateTime LocalDateTime() {return createDate;
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

}
