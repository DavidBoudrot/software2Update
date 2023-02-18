package com.david.software2.daos;

import com.david.software2.models.Contact;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ContactInterface {
    public Contact getContact() throws SQLException;
    public void addContact() throws SQLException;
    public void deleteContact() throws SQLException;
    public void updateContact() throws SQLException;
    public ObservableList<Contact> getAllContacts() throws SQLException;
}
