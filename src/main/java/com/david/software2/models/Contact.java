package com.david.software2.models;

public class Contact {
    private int contactID;
    private String contactName;
    private String email;



    public Contact(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    public int getContactID() {
        return contactID;
    }


    public String getContactName() {
        return contactName;
    }


    public String getEmail() {
        return email;
    }

}
