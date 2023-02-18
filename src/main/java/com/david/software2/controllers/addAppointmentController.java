package com.david.software2.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class addAppointmentController {

    @FXML
    private Button addAppointmentAddButton;

    @FXML
    private Button addAppointmentCancelButton;

    @FXML
    private ComboBox<?> addAppointmentContactDropDown;

    @FXML
    private Text addAppointmentContactText;

    @FXML
    private ComboBox<?> addAppointmentCustomerDropDown;

    @FXML
    private Text addAppointmentCustomerText;

    @FXML
    private TextField addAppointmentDescriptionField;

    @FXML
    private Text addAppointmentDescriptionText;

    @FXML
    private DatePicker addAppointmentEndField;

    @FXML
    private Spinner<?> addAppointmentEndHoursField;

    @FXML
    private Text addAppointmentEndHoursText;

    @FXML
    private Spinner<?> addAppointmentEndMinutesField;

    @FXML
    private Text addAppointmentEndMinutesText;

    @FXML
    private Text addAppointmentEndText;

    @FXML
    private TextField addAppointmentIDField;

    @FXML
    private Text addAppointmentIDText;

    @FXML
    private TextField addAppointmentLocationField;

    @FXML
    private Text addAppointmentLocationText;

    @FXML
    private DatePicker addAppointmentStartField;

    @FXML
    private Spinner<?> addAppointmentStartHoursField;

    @FXML
    private Text addAppointmentStartHoursText;

    @FXML
    private Spinner<?> addAppointmentStartMinutesField;

    @FXML
    private Text addAppointmentStartText;

    @FXML
    private Text addAppointmentStartMinutesText;

    @FXML
    private TextField addAppointmentTitleField;

    @FXML
    private Text addAppointmentTitleText;

    @FXML
    private TextField addAppointmentTypeField;

    @FXML
    private Text addAppointmentTypeText;

    @FXML
    private ComboBox<?> addAppointmentUserDropDown;

    @FXML
    private Text addAppointmentUserText;

    @FXML
    void addAppointmentAddButtonClick(ActionEvent event) {

    }

    @FXML
    void addAppointmentCancelButtonClick(ActionEvent event) throws Exception {
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

}
