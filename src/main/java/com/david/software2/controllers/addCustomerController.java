package com.david.software2.controllers;

import com.david.software2.daos.CountryDao;
import com.david.software2.daos.CustomerDao;
import com.david.software2.models.Alerts;
import com.david.software2.models.Country;
import com.david.software2.models.Customer;
import com.david.software2.models.Division;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class addCustomerController {

    @FXML
    private Button addCustomerAddButton;

    @FXML
    private Text addCustomerAddCustomerText;

    @FXML
    private Text addCustomerAddressText;

    @FXML
    private TextField addCustomerAddressField;

    @FXML
    private Button addCustomerCancelButton;

    @FXML
    private ChoiceBox<String> addCustomerCountryDropDown;

    @FXML
    private Text addCustomerCountryText;

    @FXML
    private ChoiceBox<String> addCustomerDivisionDropDown;

    @FXML
    private Text addCustomerDivisionText;

    @FXML
    private TextField addCustomerIDField;

    @FXML
    private Text addCustomerIDText;

    @FXML
    private TextField addCustomerNameField;

    @FXML
    private Text addCustomerNameText;

    @FXML
    private TextField addCustomerPhoneNumberField;

    @FXML
    private Text addCustomerPhoneNumberText;

    @FXML
    private TextField addCustomerPostalCodeField;

    @FXML
    private Text addCustomerPostalCodeText;


    /**
     * This method is called as soon as the scene is loaded
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        //get next available customer ID
        CustomerDao customerDao = new CustomerDao();
        int nextID = customerDao.getNextCustomerID();
        addCustomerIDField.setText(String.valueOf(nextID));
        addCustomerIDField.setEditable(false);
        setCountryCB();



        /**
         * This method makes sure everything is filled out before allowing the user to add a customer
         *
         */
    }
    private boolean getIsValid() {
        boolean isValid = true;
        if (addCustomerNameField.getText().isEmpty()) {
            isValid = false;
        }
        if (addCustomerAddressField.getText().isEmpty()) {
            isValid = false;
        }
        if (addCustomerPostalCodeField.getText().isEmpty()) {
            isValid = false;
        }
        if (addCustomerPhoneNumberField.getText().isEmpty()) {
            isValid = false;
        }
        if (addCustomerCountryDropDown.getValue() == null) {
            isValid = false;
        }
        if (addCustomerDivisionDropDown.getValue() == null) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * This method adds a customer to the database
     * @throws SQLException
     */
    @FXML
    void addCustomerAddButtonClick(ActionEvent event) throws Exception {
        if (getIsValid()){
        addCustomer();
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.tablesController.class.getResource("/com/david/software2/views/tablesView.fxml"));
        Parent root = null;
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Tables");
        stage.setX(stage.getX() - 270);
        stage.setY(stage.getY() - 100);
        stage.setScene(scene);
        stage.show();
        }
        else {
            Alerts.Alert("Error", "Please fill out all fields", "Please fill out all fields");
        }



    }


    /**
     * This method canges the scene to the tables scene.
     */

    @FXML
    void addCustomerCancelButtonClick(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.tablesController.class.getResource("/com/david/software2/views/tablesView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Tables");
        stage.setX(stage.getX() - 270);
        stage.setY(stage.getY() - 100);
        stage.setScene(scene);
        stage.show();

    }



    /**
     * This method adds or updates the customer to the database.
     * it trys to add the customer and if it fails it updates the customer.
     */

    private void addCustomer() throws SQLException {
            int id = Integer.parseInt(addCustomerIDField.getText());
            String name = addCustomerNameField.getText();
            String address = addCustomerAddressField.getText();
            String postalCode = addCustomerPostalCodeField.getText();
            String phoneNumber = addCustomerPhoneNumberField.getText();
            String country = addCustomerCountryDropDown.getValue().toString();
            String createDate = LocalDateTime.now().toString();
            //import main controller
            String createdBy = loginController.getCurrentUser().getUserName();
            String lastUpdate = LocalDateTime.now().toString();
            String lastUpdatedBy = loginController.getCurrentUser().getUserName();
            String divisionName = addCustomerDivisionDropDown.getValue();
            int divisionID = 0;
            divisionID = CountryDao.getDivisionID(divisionName);



            Customer customer = new Customer(id, name, address, postalCode, phoneNumber, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionID);
            //add to database
            CustomerDao customerDao = new CustomerDao();
            try {
                customerDao.addCustomer(customer);
                //if we cant add the customer that means the customer already exists
            } catch (SQLException throwables) {
                customerDao.updateCustomer(customer);
                //so we update the customer
            } catch (Exception e) {
                e.printStackTrace();
            } //if that fails, we print the error

        }


        /**
         * This method populates the country dropdown
         * @throws SQLException
         */
    private void setCountryCB() throws SQLException {
        CountryDao countryDao = new CountryDao();
        ObservableList<Country> countries = countryDao.getAllCountries();
        for (Country country : countries) {
            addCustomerCountryDropDown.getItems().add(country.getCountryName());
        }
    }




    //once country is selected, populate division dropdown
    /**
     * This method gets the values ready for the division dropdown and calls the divisiondropdown method
     * @throws SQLException
     */
    @FXML
    void addCustomerCountryDropDownClick(ActionEvent event) throws Exception {
        String targetCountry = addCustomerCountryDropDown.getValue();
        setDivisionCB(targetCountry);
        System.out.println(addCustomerCountryDropDown.getValue());
        setDivisionCB(targetCountry);

    }


    /**
     * This method populates the division dropdown
     * @throws SQLException
     */

    private void setDivisionCB(String targetCountry) throws SQLException {
        CountryDao countryDao = new CountryDao();

        //get all divisions for selected country

        ObservableList<Division> divisions = countryDao.getAllDivisions(targetCountry);
        System.out.println(divisions);
        addCustomerDivisionDropDown.getItems().clear();
        for (Division division : divisions) {
            addCustomerDivisionDropDown.getItems().add(division.getDivisionName());
        }

    }



    /**
     * This method sets the customer to be updated
     */
    public void setCustomer(Customer selectedCustomer) throws SQLException {
        //set text fields
        addCustomerIDField.setText(String.valueOf(selectedCustomer.getCustomerID()));
        addCustomerNameField.setText(selectedCustomer.getCustomerName());
        addCustomerAddressField.setText(selectedCustomer.getAddress());
        addCustomerPostalCodeField.setText(selectedCustomer.getPostalCode());
        addCustomerPhoneNumberField.setText(selectedCustomer.getPhone());

        //get the division name from the division id
        CountryDao CountryDao = new CountryDao();
        String divisionName = CountryDao.getDivisionName(selectedCustomer.getDivisionID());

        //disable id field
        addCustomerIDField.setDisable(true);
        //add country selection

        addCustomerCountryDropDown.setValue(CountryDao.getCountryName(divisionName));
        //add division selection
        addCustomerDivisionDropDown.setValue(divisionName);

        boolean isUpdate = true;

    }
}
