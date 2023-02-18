package com.david.software2.daos;

import com.david.software2.models.Customer;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CustomerInterface {
    public void addCustomer(Customer customer) throws SQLException;
    public void deleteCustomer(int CustomerID) throws SQLException;
    public void updateCustomer(Customer customer) throws SQLException;
    public Customer getCustomer(int CustomerID) throws SQLException;
    public ObservableList<Customer> getAllCustomers() throws SQLException;


}
