package com.david.software2.models;

/**
 * Appointment class
 */

import com.david.software2.daos.ContactDao;
import com.david.software2.daos.CustomerDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Appointment {
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerID;
    private int userID;
    private int contactID;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;

    public Appointment(int appointmentID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID, LocalDateTime creationDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Getters and setters
     */

    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Getters and setters
     */

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Getters and setters
     */

    public String getTitle() {
        return title;
    }


    /**
     * Getters and setters
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Getters and setters
     */
    public String getDescription() {
        return description;
    }


    /**
     * Getters and setters
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getters and setters
     */

    public String getLocation() {
        return location;
    }


    /**
     * Getters and setters
     */
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * Getters and setters
     */
    public String getType() {
        return type;
    }


    /**
     * Getters and setters
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * Getters and setters
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Getters and setters
     */

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Getters and setters
     */

    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Getters and setters
     */

    public void setEnd(LocalDateTime end) {
        this.end = end;
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
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Getters and setters
     */

    public int getContactID() {
        return contactID;
    }

    /**
     * Getters and setters
     */

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }


    /**
     * Getters and setters
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Getters and setters
     */

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
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


    /**
     * Getters and setters
     */
    public String getCustomerName() throws SQLException {
        String customerName = "";
        //get customername from id
        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.getCustomer(customerID);
        return customer.getCustomerName();

    }


    /**
     * Getters and setters
     */
    public String getContactName() throws SQLException {
        String contactName = "";
        //get contactname from id
        ContactDao cd = new ContactDao();
        Contact contact = cd.getContact(contactID);
        return contact.getContactName();
    }
}

