package com.david.software2.daos;

import com.david.software2.helper.JDBC;
import com.david.software2.models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDao implements ContactInterface {

    /**
     * Gets a contact from int contactID
     * @param contactID
     * @return
     * @throws SQLException
     */
    @Override
    public Contact getContact(int contactID) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, contactID);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        if (rs.next()) {
            return new Contact(rs.getInt("Contact_ID"), rs.getString("Contact_Name"), rs.getString("Email"));
        }
        return null;
    }

    /**
     * adds a contact from Contact class
     * @param contact
     * @throws SQLException
     */
    @Override
    public void addContact(Contact contact) throws SQLException {
        String sql = "INSERT INTO contacts (Contact_ID, Contact_Name, Email) VALUES (?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, contact.getContactID());
        ps.setString(2, contact.getContactName());
        ps.setString(3, contact.getEmail());
        ps.executeUpdate();
    }


    /**
     * deletes a contact from int contactID
     * @param contactID
     * @throws SQLException
     */

    @Override
    public void deleteContact(int contactID) throws SQLException {
        String sql = "DELETE FROM contacts WHERE Contact_ID = ?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, contactID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    /**
     * updates a contact from Contact class
     * @param contact
     * @throws SQLException
     */
    @Override
    public void updateContact(Contact contact) throws SQLException {
        String sql = "UPDATE contacts SET Contact_Name = ?, Email = ? WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getEmail());
        ps.setInt(3, contact.getContactID());
        ps.executeUpdate();
    }


    /**
     * gets all contacts from database
     * @return
     * @throws SQLException
     */
    @Override
    public ObservableList<Contact> getAllContacts() throws SQLException {
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        while (rs.next()) {
            contacts.add(new Contact(rs.getInt("Contact_ID"), rs.getString("Contact_Name"), rs.getString("Email")));
        }
        return contacts;


    }

    /**
     * Gets a contactID from a string value
     * @param value
     * @return
     * @throws SQLException
     */

    public int getContactID(String value) throws SQLException {
        String sql = "SELECT Contact_ID FROM contacts WHERE Contact_Name = ?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, value);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                return rs.getInt("Contact_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
