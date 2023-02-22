package com.david.software2.daos;

import com.david.software2.helper.JDBC;
import com.david.software2.models.Appointment;
import com.david.software2.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author David
 * The appointment dao is for methods that interact with the database appointments.
 */
public class AppointmentDao implements AppointmentInterface{
    /**
     * Check if current user has an appointment within 15 minutes
     * @param currentUser
     * @return
     */
    public static boolean getAppointmentWithin15Minutes(User currentUser) {
        //check if user passed has an appointment within 15 minutes
        String sql = "SELECT * FROM appointments WHERE User_ID = ? AND Start BETWEEN ? AND ?";
        PreparedStatement ps = null;
        try {
            ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, currentUser.getUserID());
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now().plusMinutes(15)));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error getting appointments");
        }
        return false;
    }
    /**
     * Adds an appointment to the database
     * @param appointment
     */
    @Override
    public void addAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, appointment.getTitle());
            ps.setString(2, appointment.getDescription());
            ps.setString(3, appointment.getLocation());
            ps.setString(4, appointment.getType());
            ps.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
            ps.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
            ps.setTimestamp(7, Timestamp.valueOf(appointment.getCreationDate()));
            ps.setString(8, appointment.getCreatedBy());
            ps.setTimestamp(9, Timestamp.valueOf(appointment.getLastUpdate()));
            ps.setString(10, appointment.getLastUpdatedBy());
            ps.setInt(11, appointment.getCustomerID());
            System.out.println(appointment.getUserID());
            ps.setInt(12, appointment.getUserID());
            ps.setInt(13, appointment.getContactID());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding appointment" + e.getMessage());
        }
    }
    /**
     * Updates an appointment in the database.
     * @param appointment
     * @throws SQLException
     */
    @Override
    public void updateAppointment(Appointment appointment) throws SQLException {
        try {
            int appointmentid = appointment.getAppointmentID();
            //update appointment in db
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, appointment.getTitle());
            ps.setString(2, appointment.getDescription());
            ps.setString(3, appointment.getLocation());
            ps.setString(4, appointment.getType());
            ps.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
            ps.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
            ps.setTimestamp(7, Timestamp.valueOf(appointment.getLastUpdate()));
            ps.setString(8, appointment.getLastUpdatedBy());
            ps.setInt(9, appointment.getCustomerID());
            ps.setInt(10, appointment.getUserID());
            ps.setInt(11, appointment.getContactID());
            ps.setInt(12, appointmentid);
            ps.executeUpdate();
        } catch (SQLException e) {
            //delete appointment and make a new one
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, appointment.getAppointmentID());
            ps.executeUpdate();
            addAppointment(appointment);

        }
    }
    /**
     * Gets an appointment from the database associated with the appointment id (Int).
     * @param AppointmentID
     * @return
     * @throws SQLException
     */
    @Override
    public Appointment getAppointment(int AppointmentID) throws SQLException {
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, AppointmentID);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
        String title = rs.getString("Title");
        String description = rs.getString("Description");
        String location = rs.getString("Location");
        String type = rs.getString("Type");
        Timestamp start = rs.getTimestamp("Start");
        LocalDateTime startLocal = start.toLocalDateTime();
        Timestamp end = rs.getTimestamp("End");
        LocalDateTime endLocal = end.toLocalDateTime();
        Timestamp createDate = rs.getTimestamp("Create_Date");
        LocalDateTime createLocal = createDate.toLocalDateTime();
        String createdBy = rs.getString("Created_By");
        Timestamp lastUpdate = rs.getTimestamp("Last_Update");
        //cast to localdatetime
        LocalDateTime lastUpdateLocal = lastUpdate.toLocalDateTime();
        String lastUpdatedBy = rs.getString("Last_Updated_By");
        int customerID = rs.getInt("Customer_ID");
        int userID = rs.getInt("User_ID");
        int contactID = rs.getInt("Contact_ID");
        Appointment appointment = new Appointment(AppointmentID, title, description, location, type, startLocal, endLocal, customerID, userID, contactID, createLocal, createdBy, lastUpdateLocal, lastUpdatedBy);
        return appointment;
    } else {
        return null;
    }
    }
    /**
     * Gets all appointments from the database.
     * Used for tables
     * @return
     * @throws SQLException
     */
    @Override
    public ObservableList<Appointment> getAllAppointments() throws SQLException {
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            LocalDateTime startLocal = start.toLocalDateTime();
            Timestamp end = rs.getTimestamp("End");
            LocalDateTime endLocal = end.toLocalDateTime();
            Timestamp createDate = rs.getTimestamp("Create_Date");
            LocalDateTime createLocal = createDate.toLocalDateTime();
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            LocalDateTime lastUpdateLocal = lastUpdate.toLocalDateTime();
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Appointment appointment = new Appointment(appointmentID, title, description, location, type, startLocal, endLocal, customerID, userID, contactID, createLocal, createdBy, lastUpdateLocal, lastUpdatedBy);
            appointments.add(appointment);
        }
        return appointments;
    }


    /**
     * Gets the next appointment id from the database.
     * @return
     * @throws SQLException
     */
    @Override
    public int getNextAppointmentID() throws SQLException {
        String sql = "SELECT MAX(Appointment_ID) FROM appointments";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            int appointmentID = rs.getInt(1);
            return appointmentID + 1;
        } else {
            return 1;
        }
    }

    /**
     * Deletes an appointment with the associated appointment id (Int).
     * @param appointmentID
     */

    public void deleteAppointment(int appointmentID) {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, appointmentID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Checks if an appointment exists in the database.
     * @param appointmentID
     * @return
     */
    public boolean appointmentExists(int appointmentID) {
        String sql = "SELECT * FROM appointments WHERE Appointment_ID = ?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, appointmentID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
