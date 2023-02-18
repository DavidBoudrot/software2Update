package com.david.software2.daos;

import com.david.software2.models.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface UserInterface {
    public void addUser() throws SQLException;
    public void deleteUser() throws SQLException;
    public void updateUser() throws SQLException;
    public User getUser() throws SQLException;
    public ObservableList<User> getAllUsers() throws SQLException;
}
