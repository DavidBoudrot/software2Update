package com.david.software2.daos;

import com.david.software2.models.Appointment;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface AppointmentInterface {
    public void addAppointment(Appointment appointment) throws SQLException;


    void updateAppointment(Appointment appointment) throws SQLException;

    public Appointment getAppointment(int AppointmentID) throws SQLException;
    public ObservableList<Appointment> getAllAppointments() throws SQLException;

    int getNextAppointmentID() throws SQLException;
}
