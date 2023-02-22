package com.david.software2.models;

import java.time.LocalDateTime;

/**
 * The user class for the user table in the database.
 */
public class User {
    private int userID;
    private String userName;
    private String password;
    private LocalDateTime accountCreationDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    public User(int userID, String userName, String password, LocalDateTime accountCreationDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.accountCreationDate = accountCreationDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * Getters and setters
     */
    public int getUserID() {
        return userID;
    }
    /**
     * Getters and setters
     */
    public String getUserName() {
        return userName;
    }
    /**
     * Getters and setters
     */
    public String getPassword() {
        return password;
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
}


