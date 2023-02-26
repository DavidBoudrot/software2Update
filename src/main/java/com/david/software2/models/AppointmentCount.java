package com.david.software2.models;

import java.time.LocalDateTime;

/**
 * Subclass of appointment that adds a count to the appointment.
 */
public class AppointmentCount  extends Appointment {
    private int appointmentCount;
    private String type;
    private String month;
    public AppointmentCount(int appointmentID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID, LocalDateTime creationDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {
        super(appointmentID, title, description, location, type, start, end, customerID, userID, contactID, creationDate, createdBy, lastUpdate, lastUpdatedBy);
    }
    public AppointmentCount(Appointment appointment) {
        super(appointment.getAppointmentID(), appointment.getTitle(), appointment.getDescription(), appointment.getLocation(), appointment.getType(), appointment.getStart(), appointment.getEnd(), appointment.getCustomerID(), appointment.getUserID(), appointment.getContactID(), appointment.getCreationDate(), appointment.getCreatedBy(), appointment.getLastUpdate(), appointment.getLastUpdatedBy());
        this.appointmentCount = 1;
    }
    public int getCount() {
        return appointmentCount;
    }
    public void setAppointmentCount(int appointmentCount) {
        this.appointmentCount = appointmentCount;
    }

}
