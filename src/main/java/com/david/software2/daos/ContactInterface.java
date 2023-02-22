package com.david.software2.daos;

import com.david.software2.models.Contact;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ContactInterface {

    //    public Contact(int contactID, String contactName, String email) {
    //        this.contactID = contactID;
    //        this.contactName = contactName;
    //        this.email = email;
    //    }
    Contact getContact(int contactID) throws SQLException;


    void addContact(Contact contact) throws SQLException;


    void deleteContact(int contactID) throws SQLException;


    void updateContact(Contact contact) throws SQLException;

    public ObservableList<Contact> getAllContacts() throws SQLException;
}
