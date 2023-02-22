package com.david.software2.controllers;

import com.david.software2.daos.ReportsDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class reportsController {

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
    private TableView<String> reportsTable;

    @FXML
    private ToggleGroup reportsToggle;

    @FXML
    private RadioButton reportsUserSchedule;


    /**
    * exit button click that switches to tables view
     */
    @FXML
    void reportsExitButtonClick(ActionEvent event) throws Exception {
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
     * Method that switches to the reportsAppointmentTypes scene
     */
    public void showAppointmentTypes(ActionEvent actionEvent) throws SQLException, IOException {
        //switch to scene reportsAppointmentTypes
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.reportsAppointmentTypesController.class.getResource("/com/david/software2/views/reportsAppointmentTypes.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Appointment Types");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Method that switches to the reportsAppointmentMonths scene
     */
        public void showAppointmentMonths(ActionEvent actionEvent) throws SQLException, IOException {
        //switch to scene reportsAppointmentMonths
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.reportsAppointmentMonthsController.class.getResource("/com/david/software2/views/reportsAppointmentMonths.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Appointment Months");
        stage.setScene(scene);
        stage.show();
        }


        /**
         * Method that switches to the reportsUserSchedule scene
         */
        public void showUserSchedule(ActionEvent actionEvent) throws SQLException, IOException {
        //switch to scene reportsUserSchedule
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.reportsUserScheduleController.class.getResource("/com/david/software2/views/reportsUserSchedule.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("User Schedule");
        stage.setScene(scene);
        stage.show();

        }


        /**
         * Method that switches to the reportsContactSchedule scene
         */
        public void showContactSchedule(ActionEvent actionEvent) throws SQLException, IOException {
        //switch to scene reportsContactSchedule
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.reportsContactScheduleController.class.getResource("/com/david/software2/views/reportsContactSchedule.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Contact Schedule");
        stage.setScene(scene);
        stage.show();


            }
        }
