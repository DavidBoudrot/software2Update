package com.david.software2.controllers;

import com.david.software2.daos.AppointmentDao;
import com.david.software2.daos.ReportsDao;
import com.david.software2.daos.UserDao;
import com.david.software2.models.Appointment;
import com.david.software2.models.User;
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
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.SQLException;

public class reportsUserScheduleController {

    @FXML
    private TableColumn<?, ?> CustomerIDColumn;

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
    private RadioButton reportsUserSchedule;

    @FXML
    private TableColumn<?, ?> startDateColumn;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private ComboBox<User> userComboBox;

    @FXML
    private TableColumn<?, ?> userIDColumn;

    @FXML
    private TableView<Appointment> usersScheduleTable;

    /**
     * Initializes the controller class.
     */
    public void initialize() throws SQLException {
        UserDao userDao = new UserDao();
        ObservableList<User> users = FXCollections.observableArrayList();
        users = userDao.getAllUsers();
        userComboBox.setItems(users);
        //set user combobox to first user
        userComboBox.setValue(users.get(0));
        //get user from combobox
        String userName = userComboBox.getValue().getUserName();
        User user = userDao.getUser(userName);
        ReportsDao rd = new ReportsDao();
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        appointments = rd.userAppointments(user);
        usersScheduleTable.setItems(appointments);
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        userIDColumn.setText("User ID");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        //make the appearance of the combobox show the user name
        userComboBox.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User user) {
                return user.getUserName();
            }

            @Override
            public User fromString(String s) {
                return null;
            }
        });

    }


    /**
     * This method switches to the tables view
     */
    @FXML
    void reportsExitButtonClick(ActionEvent event) throws IOException {
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
     * This method switches to the reportsMonth view
     */
    @FXML
    void showAppointmentMonths(ActionEvent event) throws IOException {
        //switch to scene reportsAppointmentMonths
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.reportsAppointmentMonthsController.class.getResource("/com/david/software2/views/reportsAppointmentMonths.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Appointment Months");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This method switches to the reportsTypes view
     */
    @FXML
    void showAppointmentTypes(ActionEvent event) throws IOException {
        //switch to scene reportsAppointmentTypes
        FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.reportsAppointmentTypesController.class.getResource("/com/david/software2/views/reportsAppointmentTypes.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Appointment Types");
        stage.setScene(scene);
        stage.show();

    }


    /**
     * This method switches to the reportsContactSchedule view
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
     *
     * This method switches to the reportsUserSchedule view.
     * In this case it does nothing since were already in that view.
     */
    @FXML
    void showUserSchedule(ActionEvent event) {



    }


    /**
     * This method gets the user from the combobox and displays their schedule
     */
    @FXML
    void userComboBoxClick(ActionEvent event) throws SQLException {
        //get user from combobox
        User user = userComboBox.getValue();
        ReportsDao rd = new ReportsDao();
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        appointments = rd.userAppointments(user);
        usersScheduleTable.setItems(appointments);
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));

    }

}
