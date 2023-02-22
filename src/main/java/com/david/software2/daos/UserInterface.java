package com.david.software2.daos;

import com.david.software2.models.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface UserInterface {
    public void addUser() throws SQLException;
    public void deleteUser() throws SQLException;
    public void updateUser() throws SQLException;

    String getUserName(User user) throws SQLException;

    User getUser(int userID) throws SQLException;

    public User getUser(String username) throws SQLException;
    public ObservableList<User> getAllUsers() throws SQLException;
}
