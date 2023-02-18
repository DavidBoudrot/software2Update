package com.david.software2.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class tablesController {

    @FXML
    private Button tablesAddAppointmentButton;

    @FXML
    private Button tablesAddCustomerButton;

    @FXML
    private RadioButton tablesAllButton;

    @FXML
    private TableView<?> tablesAppointmentTable;

    @FXML
    private TableColumn<?, ?> tablesAppointmentTableContactColumn;

    @FXML
    private TableColumn<?, ?> tablesAppointmentTableCustomerIDColumn;

    @FXML
    private TableColumn<?, ?> tablesAppointmentTableDescriptionColumn;

    @FXML
    private TableColumn<?, ?> tablesAppointmentTableEndColumn;

    @FXML
    private TableColumn<?, ?> tablesAppointmentTableIDColumn;

    @FXML
    private TableColumn<?, ?> tablesAppointmentTableLocationColumn;

    @FXML
    private TableColumn<?, ?> tablesAppointmentTableStartColumn;

    @FXML
    private TableColumn<?, ?> tablesAppointmentTableTitleColumn;

    @FXML
    private TableColumn<?, ?> tablesAppointmentTableTypeColumn;

    @FXML
    private TableColumn<?, ?> tablesAppointmentTableUserIDColumn;

    @FXML
    private Text tablesAppointmentText;

    @FXML
    private DatePicker tablesAppointmentsDateField;

    @FXML
    private TableView<?> tablesCustomerTable;

    @FXML
    private TableColumn<?, ?> tablesCustomerTableAddressColumn;

    @FXML
    private TableColumn<?, ?> tablesCustomerTableIDColumn;

    @FXML
    private TableColumn<?, ?> tablesCustomerTableNameColumn;

    @FXML
    private TableColumn<?, ?> tablesCustomerTablePhoneNumberColumn;

    @FXML
    private TableColumn<?, ?> tablesCustomerTablePostalCodeColumn;

    @FXML
    private TableColumn<?, ?> tablesCustomerTableStateColumn;

    @FXML
    private Text tablesCustomerText;

    @FXML
    private Button tablesDeleteAppointmentButton;

    @FXML
    private Button tablesDeleteCustomerButton;

    @FXML
    private Button tablesLogoutButton;

    @FXML
    private RadioButton tablesMonthButton;

    @FXML
    private Button tablesReportButton;

    @FXML
    private Text tablesSchedulingText;

    @FXML
    private Button tablesUpdateAppointmentButton;

    @FXML
    private Button tablesUpdateCustomerButton;

    @FXML
    private RadioButton tablesWeekButton;

    @FXML
    void tablesAddAppointmentButtonClick(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(tablesController.class.getResource("/com/david/software2/views/addAppointmentView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.setX(stage.getX() + 270);
        stage.setY(stage.getY() + 100);
        stage.show();
    }

    @FXML
    void tablesAddCustomerButtonClick(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(tablesController.class.getResource("/com/david/software2/views/addCustomerView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.setX(stage.getX() + 270);
        stage.setY(stage.getY() + 100);
        stage.show();

    }

    @FXML
    void tablesAllButtonClick(ActionEvent event) {

    }

    @FXML
    void tablesAppointmentsDateFieldClick(ActionEvent event) {

    }

    @FXML
    void tablesDeleteButtonClick(ActionEvent event) {

    }

    @FXML
    void tablesDeleteCustomerButtonClick(ActionEvent event) {

    }

    @FXML
    void tablesLogoutButtonClick(ActionEvent event) throws Exception {
        //switch to login view
        FXMLLoader fxmlLoader = new FXMLLoader(tablesController.class.getResource("/com/david/software2/views/loginView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.setX(stage.getX() + 270);
        stage.setY(stage.getY() + 100);
        stage.show();


    }

    @FXML
    void tablesMonthButtonClick(ActionEvent event) {

    }

    @FXML
    void tablesReportsButtonClick(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(tablesController.class.getResource("/com/david/software2/views/reportsView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.setX(stage.getX() + 270);
        stage.setY(stage.getY() + 100);
        stage.show();


    }

    @FXML
    void tablesUpdateAppointmentButtonClick(ActionEvent event) throws Exception {


    }

    @FXML
    void tablesUpdateCustomerButtonClick(ActionEvent event) {

    }

    @FXML
    void tablesWeekButtonClick(ActionEvent event) {

    }

}
