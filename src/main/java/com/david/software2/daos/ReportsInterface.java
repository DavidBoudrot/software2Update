package com.david.software2.daos;

import com.david.software2.models.Appointment;
import com.david.software2.models.AppointmentCount;
import com.david.software2.models.AppointmentMonth;
import com.david.software2.models.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ReportsInterface  {



    //report logins by user

    //report appointment types

    //report appointment month

    //report contact schedule

    //report user appointments in next 15 minutes


    public ObservableList<AppointmentCount> reportAllAppointmentTypes() throws SQLException;

    public ObservableList<AppointmentMonth> reportAllMonths() throws SQLException;


    public ObservableList<Appointment> contactSchedule(int contactID) throws SQLException;

    public ObservableList<Appointment> userAppointments(User user) throws SQLException;


}
