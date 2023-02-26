package com.david.software2.controllers;

import com.david.software2.daos.ReportsDao;
import com.david.software2.models.AppointmentCountMonthType;
import com.david.software2.models.AppointmentMonth;
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

public class reportsAppointmentMonthsController {

    @FXML
    private TableColumn<AppointmentCountMonthType, String> MonthsColumn;

    @FXML
    private TableColumn<AppointmentCountMonthType, String> MonthsColumn1;

    @FXML
    private TableColumn<AppointmentCountMonthType, String> MonthsColumn2;

    @FXML
    private TableView<AppointmentCountMonthType> appointmentTypes;

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



    /**
     * Initializes the controller class.
     */
    public void initialize() throws SQLException {
        MonthsColumn2.setCellValueFactory(new PropertyValueFactory<>("Month"));;
        MonthsColumn1.setCellValueFactory(new PropertyValueFactory<>("Type"));;
        MonthsColumn.setCellValueFactory(new PropertyValueFactory<>("Count"));

        MonthsColumn2.setText("Month");
        MonthsColumn1.setText("Type");
        MonthsColumn.setText("Month Count");
        ReportsDao reportsDao = new ReportsDao();
        ObservableList<AppointmentCountMonthType> appointmentMonth = FXCollections.observableArrayList();
        appointmentMonth = reportsDao.reportAllMonths();
        //get a list of appointmentMonth objects with the property values of month and month count
        //set the tableview to the list of appointmentMonth objects
        appointmentTypes.setItems(appointmentMonth);
        //refresh the tableview
        appointmentTypes.refresh();




        appointmentTypes.refresh();



    }

    /**
     * Method called for when the exit button is clicked
     * it switches to the tables view
     */
    @FXML
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
     * This swithes to the months view
     * In this case it does nothing since we are already on the months view
     */
    @FXML
    void showAppointmentMonths(ActionEvent event) {
        //This does nothing since we are already on the appointment months view


    }


    /**
     * This switches to the appointment types view
     */
    @FXML
    void showAppointmentTypes(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.reportsAppointmentTypesController.class.getResource("/com/david/software2/views/reportsAppointmentTypes.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Appointment Types");
        stage.setScene(scene);
        stage.show();


    }

    /**
     * This switches to the contact schedule view
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
     * This switches to the user schedule view
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

}
