package com.david.software2.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.stream.Stream;

import com.david.software2.helper.JDBC;
import com.david.software2.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDao implements UserInterface {


    /**
     * The addUser method never had to be implemented
     * @throws SQLException
     */
    @Override
    public void addUser() throws SQLException {
        // TODO Auto-generated method stub

    }


    /**
     * The deleteUser method never had to be implemented
     * @throws SQLException
     */
    @Override
    public void deleteUser() throws SQLException {
        // TODO Auto-generated method stub

    }

    /**
     * The updateUser method never had to be implemented
     * @throws SQLException
     */
    @Override
    public void updateUser() throws SQLException {
        // TODO Auto-generated method stub

    }


    /**
     * Gets a username from a user.
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public String getUserName(User user) throws SQLException {
        String sql = "SELECT User_Name FROM users WHERE User_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, user.getUserID());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("User_Name");
        } else {
            return null;
        }
    }


    /**
     * Gets a user from int userID
     * @param userID
     * @return
     * @throws SQLException
     */
    @Override
    public User getUser(int userID) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, userID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            Timestamp accountCreationDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            User user = new User(userID, userName, password, accountCreationDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy);
            return user;
        } else {
            return null;
        }
    }



    /**
     * Gets a user from a username
     * @param username
     * @return
     * @throws SQLException
     */
    @Override
    public User getUser(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = ?"; // SQL query
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql); // get connection and prepare statement
        ps.setString(1, username); // I believe this converts the string to a data type that the database can understand
        ResultSet rs = ps.executeQuery(); // get the result set

        System.out.println(rs);
        if (rs.next()) {  // if there is a result
            System.out.println("User found");
            int userID = rs.getInt("User_ID"); //create a new user with the results
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            Timestamp accountCreationDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            User user = new User(userID, userName, password, accountCreationDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy);
            return user;

        } else {
            System.out.println("User not found");
            return null;
        }
    }

    /**
     * LAMBDA USED HERE!!!
     * Gets all users from the database
     * @return
     * @throws SQLException
     */
    @Override
    public ObservableList<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<User> users = FXCollections.observableArrayList();
        Stream.generate(() -> {
            try {
                if (rs.next()) {
                    int userID = rs.getInt("User_ID");
                    String userName = rs.getString("User_Name");
                    String password = rs.getString("Password");
                    Timestamp accountCreationDate = rs.getTimestamp("Create_Date");
                    String createdBy = rs.getString("Created_By");
                    Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                    String lastUpdatedBy = rs.getString("Last_Updated_By");
                    return new User(userID, userName, password, accountCreationDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy);
                } else {
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).takeWhile(Objects::nonNull).forEach(users::add);
        return users;
    }


    /**
     * Gets a userID from a username
     * @param value
     * @return
     * @throws SQLException
     */

    public int getUserID(String value) throws SQLException {
        String sql = "SELECT User_ID FROM users WHERE User_Name = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, value);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("User_ID");
        } else {
            return 0;
        }
    }
}
