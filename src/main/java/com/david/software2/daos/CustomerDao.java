package com.david.software2.daos;

import com.david.software2.models.Alerts;
import com.david.software2.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import com.david.software2.helper.JDBC;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * @author David
 * The CustomerDao class is used to interact with the database and the Customer class.
 */
public class CustomerDao  implements CustomerInterface {

    /**
     * The add customer method is used to add a customer to the database.
     */
    @Override
    public void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customer.getCustomerID());
        ps.setString(2, customer.getCustomerName());
        ps.setString(3, customer.getAddress());
        ps.setString(4, customer.getPostalCode());
        ps.setString(5, customer.getPhone());
        ps.setString(6, customer.getCreateDate());
        ps.setString(7, customer.getCreatedBy());
        ps.setString(8, customer.getLastUpdate());
        ps.setString(9, customer.getLastUpdatedBy());
        ps.setInt(10, customer.getDivisionID());
        ps.executeUpdate();


    }


    /**
     * The delte customer class is used to delete a customer from the database.
     * @param CustomerID
     * @throws SQLException
     */
    @Override
    public void deleteCustomer(int CustomerID) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, CustomerID);
        ps.executeUpdate();

    }


    /**
     * The update customer method is used to update a customer in the database.
     * @param customer
     * @throws SQLException
     */

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setString(5, customer.getLastUpdate());
        ps.setString(6, customer.getLastUpdatedBy());
        ps.setInt(7, customer.getDivisionID());
        ps.setInt(8, customer.getCustomerID());
        ps.executeUpdate();
    }


    /**
     * The getCustomer method is used to get a customer from customer ID.
     * @param CustomerID
     * @return
     * @throws SQLException
     */
    @Override
    public Customer getCustomer(int CustomerID) throws SQLException {
        String sql = "SELECT * FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, CustomerID);
        ResultSet rs = ps.executeQuery();
        Customer customer = null;
        if (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getString("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionID = rs.getInt("Division_ID");
            customer = new Customer(customerID, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionID);
            return customer;
        }
        return null;
    }


    /**
     * The getAllCustomers method is used to get all customers from the database.
     * @return
     * @throws SQLException
     */
    @Override
    public ObservableList<Customer> getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        //create list of customers
        ObservableList<Customer> customers = FXCollections.observableArrayList();


        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getString("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionID = rs.getInt("Division_ID");
            Customer customer = new Customer(customerID, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionID);
            customers.add(customer);
        }
        return customers;

    }

    /**
     * The get division name method is used to get the division name from the division ID.
     * @param divisionID
     * @return
     * @throws SQLException
     */


    public Object getDivisionName(int divisionID) throws SQLException {
        String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, divisionID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String divisionName = rs.getString("Division");
            return divisionName;
        }
        return null;
    }

    /**
     * The get next customer ID method is used to get the next customer ID.
     * @return
     * @throws SQLException
     */
    public int getNextCustomerID() throws SQLException {
        String sql = "SELECT MAX(Customer_ID) FROM customers";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int customerID = rs.getInt("MAX(Customer_ID)");
            return customerID + 1;
        }
        return 0;
    }


    /**
     * The get customerID method is used to get the customer ID from the customer name.
     * @param value
     * @return
     * @throws SQLException
     */
    public int getCustomerID(String value) throws SQLException {
        String sql = "SELECT Customer_ID FROM customers WHERE Customer_Name = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, value);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            return customerID;
        }
        return 0;

    }

    /**
     * The hasAppointments method returns a boolean value for if the customer has appointments.
     * @param selectedCustomer
     * @return
     * @throws SQLException
     */
    public boolean hasAppointments(Customer selectedCustomer) throws SQLException {
        //check if customer has appointments
        String sql = "SELECT * FROM appointments WHERE Customer_ID = ?";
                PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
                ps.setInt(1, selectedCustomer.getCustomerID());
                ResultSet rs = ps.executeQuery();
            return rs.next();
            }
    }

