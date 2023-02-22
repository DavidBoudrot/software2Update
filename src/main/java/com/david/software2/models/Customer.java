package com.david.software2.models;

import com.david.software2.daos.DivisionDao;

import java.sql.SQLException;

/**
 * Customer class for customer models made from database
 */
public class Customer {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;
    /**
     * Constructer for the customer class
     */
    public Customer(int customerID, String customerName, String address, String postalCode, String phone, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy, int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }
    /**
     * Getters and setters
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Getters and setters
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * Getters and setters
     */
    public String getAddress() {
        return address;
    }
    /**
     * Getters and setters
     */
    public String getPostalCode() {
        return postalCode;
    }
    /**
     * Getters and setters
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Getters and setters
     */
    public String getCreateDate() {
        return createDate;
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
    public String getLastUpdate() {
        return lastUpdate;
    }
    /**
     * Getters and setters
     */
    public void setLastUpdate(String lastUpdate) {
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
    public int getDivisionID() {
        return divisionID;
    }
}
