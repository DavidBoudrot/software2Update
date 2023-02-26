package com.david.software2.daos;

import com.david.software2.helper.JDBC;
import com.david.software2.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * The reports dao is used to make queries to the database for reports.
 */
public class ReportsDao implements ReportsInterface {



    /**
     * This method returns a list of all appointments grouped by type.
     * LAMBDA USED
     * @return
     * @throws SQLException
     */

    @Override
    public ObservableList<AppointmentCount> reportAllAppointmentTypes() throws SQLException {

        //get all appointments
        AppointmentDao appointmentDao = new AppointmentDao();
        ObservableList<Appointment> Allappointments = FXCollections.observableArrayList();
        Allappointments = appointmentDao.getAllAppointments();
        //cast to appointment count

        // LAMBDA used here!!
        //This just makes a new AppointmentCount object for each appointment in the list.
        //I think it looks cleaner than a for loop.
        ObservableList<AppointmentCount> allAppointments = FXCollections.observableArrayList();
        Allappointments.forEach(appointment -> {
            AppointmentCount appointmentCount = new AppointmentCount(appointment);
            allAppointments.add(appointmentCount);
        });


        ObservableList<AppointmentCount> targetAppointments = FXCollections.observableArrayList();
        //Now that I have casted the appointments to a class with a count variable, I can use a for loop to count the number of times each appointment type occurs.
        //In the end I want a list of AppointmentCount objects that have unique appointment types and a count of how many times they occur.

        for (AppointmentCount appointment : allAppointments) {
            if (targetAppointments.isEmpty()) { //if the list is empty, add the first appointment to the list
                targetAppointments.add(appointment);
            } else {
                boolean unique = true;
                for (AppointmentCount targetAppointment : targetAppointments) {
                    if (appointment.getType().equals(targetAppointment.getType())) { //If the appointment type is already in the list, increment the count for that appointment type.
                        unique = false;
                        //If its not unique we need to go back to the first instance of that appointment type and increment the count.
                        int index = targetAppointments.indexOf(targetAppointment);
                        targetAppointments.get(index).setAppointmentCount(targetAppointment.getCount() + 1);
                    }
                }
                if (unique) { //if the appointment type is not in the list, add it to the list
                    targetAppointments.add(appointment);
                }
            }
        }


        //create new class that will extend appointment and add a count variable


        return targetAppointments;
    }

    /**
     * This method returns a list of all appointments grouped by month.
     *
     * @return
     * @throws SQLException
     */
        @Override
        public ObservableList<AppointmentCountMonthType> reportAllMonths () throws SQLException {

            //sql query to get the appointment count by type for each month
            //get all appointments
            String sql = "SELECT MONTHNAME(start) AS month, type, COUNT(*) AS count FROM appointments GROUP BY MONTHNAME(start), type";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ObservableList<AppointmentCountMonthType> appointmentCountMonthTypes = FXCollections.observableArrayList();
            while (rs.next()) {
                String month = rs.getString("month");
                String type = rs.getString("type");
                int count = rs.getInt("count");
                AppointmentCountMonthType appointment = new AppointmentCountMonthType(month, type, count);
                appointmentCountMonthTypes.add(appointment);
            }
            return appointmentCountMonthTypes;
        }



    /**
     * This method returns a list of all appointments for a given contact.
     * @param contactID
     * @return
     * @throws SQLException
     */
        @Override
        public ObservableList<Appointment> contactSchedule ( int contactID) throws SQLException {
        // get all appointments with contactID
            AppointmentDao appointmentDao = new AppointmentDao();
            ObservableList<Appointment> Allappointments = FXCollections.observableArrayList();
            Allappointments = appointmentDao.getAllAppointments(); // get all appointments
            ObservableList<Appointment> targetAppointments = FXCollections.observableArrayList();
            for (Appointment appointment : Allappointments) { // loop through all appointments
                if (appointment.getContactID() == contactID) { // if the appointment has the contactID we are looking for, add it to the target list
                    targetAppointments.add(appointment);
                }
            }
            return targetAppointments;
        }


    /**
     * this method returns a list of all appointments for a given user.
     * LAMBDA used here!!
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public ObservableList<Appointment> userAppointments(User user) throws SQLException {
        //get all appointments with userID
        AppointmentDao appointmentDao = new AppointmentDao();
        ObservableList<Appointment> allAppointments = appointmentDao.getAllAppointments(); // get all appointments
        ObservableList<Appointment> targetAppointments = allAppointments.stream()
                .filter(appointment -> appointment.getUserID() == user.getUserID())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        return targetAppointments;
    }
}
