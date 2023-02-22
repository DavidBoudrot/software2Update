package com.david.software2.controllers;

import com.david.software2.daos.ReportsDao;
import com.david.software2.models.Appointment;
import com.david.software2.models.AppointmentCount;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class reportsAppointmentTypesController {

    @FXML
    private TableView<AppointmentCount> appointmentTypesTable;

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
    private TableColumn<AppointmentCount, String> typeColumn;

    @FXML

    /**
     * exit button click that switches to tables view
     */
    void reportsExitButtonClick(ActionEvent event) throws IOException {

        //switch to table view
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.tablesController.class.getResource("/com/david/software2/views/tablesView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Tables");
        stage.setScene(scene);
        stage.show();

    }


    /**
     * switch to the appointment months view.
     * @param event
     * @throws IOException
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
     * Switch to the appointment types view
     * In this case it is already on the appointment types view so the block is empty.
     * @param event
     */
    @FXML
    void showAppointmentTypes(ActionEvent event) {

    }


    /**
     * Switch to the contact schedule view
     */
    @FXML
    void showContactSchedule(ActionEvent event) throws IOException {
        //switch to scene reportsContactSchedule
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.reportsContactScheduleController.class.getResource("/com/david/software2/views/reportsContactSchedule.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Contact Schedule");
        stage.setScene(scene);
        stage.show();

    }


    /**
     * Switch to userschedule view
     */
    @FXML
    void showUserSchedule(ActionEvent event) throws IOException {
        //switch to scene reportsUserSchedule
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.reportsUserScheduleController.class.getResource("/com/david/software2/views/reportsUserSchedule.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("User Schedule");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Initialize the table view with the data from the database.
     * Also a comment on an issue I ran into.
     *
     */
    public void initialize() throws SQLException {
        //create another column
        TableColumn<AppointmentCount, Integer> countColumn = new TableColumn<>("Count");
        //add the column to the table
        appointmentTypesTable.getColumns().add(countColumn);
        ReportsDao rd = new ReportsDao();
        ObservableList<AppointmentCount> appointmentTypes = FXCollections.observableArrayList();
        appointmentTypes = rd.reportAllAppointmentTypes();
        appointmentTypesTable.setItems(appointmentTypes);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        //Heres a problem I ran into, I added a column to the table view and the first column was too wide.
        //My solution is below for decreasing the width of the first column.
        typeColumn.setPrefWidth(400);
        countColumn.setPrefWidth(150);
        appointmentTypesTable.refresh();
        }
    }

