package com.david.software2.controllers;

import com.david.software2.daos.ContactDao;
import com.david.software2.daos.ReportsDao;
import com.david.software2.models.Appointment;
import com.david.software2.models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class reportsContactScheduleController {

    @FXML
    private TableColumn<?, ?> CustomerIDColumn;

    @FXML
    private ComboBox<String> contactComboBox;

    @FXML
    private TableColumn<?, ?> contactIDColumn;

    @FXML
    private TableView<Appointment> contactsScheduleTable;

    @FXML
    private TableColumn<?, ?> descColumn;

    @FXML
    private TableColumn<?, ?> endDateColumn;



    @FXML
    private RadioButton reportsContactSchedule;

    @FXML
    private RadioButton reportsCustomerAppointmentMonths;

    @FXML
    private RadioButton reportsCustomerAppointmentTypes;

    @FXML
    private Button reportsExitButton;

    @FXML
    private Text reportsReportsText;

    @FXML
    private ToggleGroup reportsToggle;

    @FXML
    private RadioButton reportsUserLogins;

    @FXML
    private TableColumn<?, ?>startDateColumn;



    @FXML
    private TableColumn<?, ?>titleColumn;

    @FXML
    private TableColumn<?, ?> typeColumn;


    /**
     * Initializes the controller class.
     * It sets the tableview to the appointments for the selected contact
     */


    //init
    public void initialize() throws Exception {
        contactIDColumn.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
        CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("End"));


        //add contacts to combobox
        ContactDao cd = new ContactDao();
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        contacts = cd.getAllContacts();
        //for each contact in contacts, add an item to the combobox
        for (Contact c : contacts) {
            contactComboBox.getItems().add(c.getContactName());
        }



    }

    /**
     * This method is called when the user clicks the contact combobox
     * It sets the tableview to the appointments for the selected contact
     */
    @FXML
    void contactComboBoxClick(ActionEvent event) throws SQLException {
        String selectedItem = contactComboBox.getSelectionModel().getSelectedItem();
        ContactDao cd = new ContactDao();
        int contactID = cd.getContactID(selectedItem);
        ReportsDao reportsDao = new ReportsDao();
        ObservableList<Appointment> contactapps = FXCollections.observableArrayList();
        contactapps = reportsDao.contactSchedule(contactID);
        contactsScheduleTable.setItems(contactapps);
        contactsScheduleTable.refresh();
        //set the tableview to the appointments for the selected contact



    }


    /**
     * This method is called when the user clicks the exit button
     * It exits to the tables view
     */
    @FXML
    void reportsExitButtonClick(ActionEvent event) throws Exception {
        //exit to tables view
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.tablesController.class.getResource("/com/david/software2/views/tablesView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();



    }

    /**
     * This method is called when the user clicks the user logins radio button
     * It switches to appointment months view
     */
    @FXML
    void showAppointmentMonths(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.reportsAppointmentMonthsController.class.getResource("/com/david/software2/views/reportsAppointmentMonths.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Appointment Months");
        stage.setScene(scene);
        stage.show();

    }


    /**
     * This method is called when the user clicks the show appointment types radio button
     * It switches to appointment types view
     */
    @FXML
    void showAppointmentTypes(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.reportsAppointmentTypesController.class.getResource("/com/david/software2/views/reportsAppointmentTypes.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Appointment Types");
        stage.setScene(scene);
        stage.show();

    }


    /**
     * This method is called when the user clicks the show contact schedule radio button
     * In this case it does nothing.
     */
    @FXML
    void showContactSchedule(ActionEvent event) {

    }


    /**
     * This method is called when the user clicks the show user schedule button
     * It switches to user schedule view
     */
    public void showUserSchedule(ActionEvent actionEvent) throws IOException {
        //switch to scene reportsUserSchedule
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.reportsUserScheduleController.class.getResource("/com/david/software2/views/reportsUserSchedule.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("User Schedule");
        stage.setScene(scene);
        stage.show();
    }
}
