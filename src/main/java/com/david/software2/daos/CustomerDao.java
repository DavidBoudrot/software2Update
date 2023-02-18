package com.david.software2.daos;

import com.david.software2.models.Customer;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.david.software2.helper.JDBC;

public class CustomerDao  implements CustomerInterface {

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        // add customer to database

        }




    @Override
    public void deleteCustomer(int CustomerID) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        // TODO Auto-generated method stub

    }

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
            customer = new Customer(customerID,customerName,address,postalCode,phone,createDate,createdBy,lastUpdate,lastUpdatedBy,divisionID);
            return customer;
        }
        return null;
    }

    @Override
    public ObservableList<Customer> getAllCustomers() throws SQLException {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Customer> customers = null;

        while(rs.next()) {
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
            Customer customer = new Customer(customerID,customerName,address,postalCode,phone,createDate,createdBy,lastUpdate,lastUpdatedBy,divisionID);
            customers.add(customer);
        }
        return customers;

    }



}
