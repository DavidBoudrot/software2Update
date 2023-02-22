package com.david.software2.controllers;

import com.david.software2.daos.AppointmentDao;
import com.david.software2.daos.ContactDao;
import com.david.software2.daos.CustomerDao;
import com.david.software2.daos.UserDao;
import com.david.software2.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;

public class addAppointmentController {
    /**
     * This is the  method called when the user clicks the add appointment button.
     * It will add the appointment to the database and return the user to the main screen.
     * @param event
     * @throws SQLException
     */

    @FXML
    private Button addAppointmentAddButton;

    @FXML
    private Button addAppointmentCancelButton;

    @FXML
    private ComboBox<String> addAppointmentContactDropDown;

    @FXML
    private Text addAppointmentContactText;

    @FXML
    private ComboBox<String> addAppointmentCustomerDropDown;

    @FXML
    private Text addAppointmentCustomerText;

    @FXML
    private TextField addAppointmentDescriptionField;

    @FXML
    private Text addAppointmentDescriptionText;

    @FXML
    private DatePicker addAppointmentEndField;

    @FXML
    private Spinner<Integer> addAppointmentEndHoursField;

    @FXML
    private Text addAppointmentEndHoursText;

    @FXML
    private Spinner<Integer> addAppointmentEndMinutesField;

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
    private Spinner<Integer> addAppointmentStartHoursField;

    @FXML
    private Text addAppointmentStartHoursText;

    @FXML
    private Spinner<Integer> addAppointmentStartMinutesField;

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
    private boolean update = false;

    @FXML
    private ComboBox<User> addAppointmentUserDropDown;

    @FXML
    private Text addAppointmentUserText;

    /**
     * The initialize method is called as soon as the scene is loaded.
     * It populates the dropdowns and sets the spinners.
     */
    @FXML
    void initialize() throws SQLException {
        //Populate the user dropdown
        UserDao ud = new UserDao();
        addAppointmentUserDropDown.setConverter(new StringConverter<User>() {
            @Override
            public User fromString(String string) {
                //get the user object from the string
                UserDao ud = new UserDao();
                try {
                    User user = ud.getUser(string);
                    return user;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
            @Override
            //convert the user object to a string
            public String toString(User object) {
                if (object == null) {
                    return null;
                } else {
                    return object.getUserName();
                }
            }
            //convert the string to a user object
        });
        ObservableList<User> users = ud.getAllUsers();
        addAppointmentUserDropDown.getItems().addAll(users);
        //set the converter for the user dropdown
        CustomerDao cd = new CustomerDao();
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        customers = cd.getAllCustomers();
        ObservableList<String> customerNames = FXCollections.observableArrayList();
        for (Customer customer : customers) {
            String customerName = customer.getCustomerName();
            customerNames.add(customerName);
        }
        addAppointmentCustomerDropDown.getItems().addAll(customerNames);
        //Populate the contact dropdown
        ContactDao cod = new ContactDao();
        ObservableList<String> contactNames = FXCollections.observableArrayList();
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        allContacts = cod.getAllContacts();
        for (Contact contact : allContacts) {
            String contactName = contact.getContactName();
            contactNames.add(contactName);
        }
        addAppointmentContactDropDown.getItems().addAll(contactNames);
        //set next appointment id
        AppointmentDao ad = new AppointmentDao();
        int nextAppointmentID = ad.getNextAppointmentID();
        addAppointmentIDField.setText(String.valueOf(nextAppointmentID));
        //make sure the user can't edit the id field
        addAppointmentIDField.setEditable(false);
        //make the start and end date pickers default to today
        addAppointmentStartField.setValue(java.time.LocalDate.now());
        addAppointmentEndField.setValue(java.time.LocalDate.now());
        //make the start and end time spinners default to 8:00
        addAppointmentStartHoursField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 8));
        addAppointmentStartMinutesField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        addAppointmentEndHoursField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 8));
        addAppointmentEndMinutesField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));

        addAppointmentStartHoursField.getValueFactory().setValue(8);
        addAppointmentStartMinutesField.getValueFactory().setValue(0);
        addAppointmentEndHoursField.getValueFactory().setValue(8);


    }
    /**
     * my checkValues method checks to make sure all the fields are filled out and also does logic checks on the time.
     * It has to be within business hours and it cant end before it starts.
     */
    private boolean checkValues() throws SQLException {
        boolean valid = true;
        if (addAppointmentTitleField.getText().isEmpty()) {
            valid = false;
        }
        if (addAppointmentDescriptionField.getText().isEmpty()) {
            valid = false;
        }
        if (addAppointmentLocationField.getText().isEmpty()) {
            valid = false;
        }
        if (addAppointmentTypeField.getText().isEmpty()) {
            valid = false;
        }
        if (addAppointmentStartField.getValue() == null) {
            valid = false;
        }
        if (addAppointmentEndField.getValue() == null) {
            valid = false;
        }
        if (addAppointmentStartHoursField.getValue() == null) {
            valid = false;
        }
        if (addAppointmentStartMinutesField.getValue() == null) {
            valid = false;
        }
        if (addAppointmentEndHoursField.getValue() == null) {
            valid = false;
        }
        if (addAppointmentEndMinutesField.getValue() == null) {
            valid = false;
        }
        if (addAppointmentCustomerDropDown.getValue() == null) {
            valid = false;
        }
        if (addAppointmentUserDropDown.getValue() == null) {
            valid = false;
        }
        if (addAppointmentContactDropDown.getValue() == null) {
            valid = false;
        }
        //check if the start time is before the end time
        if (addAppointmentStartField.getValue().isAfter(addAppointmentEndField.getValue())) {
            valid = false;
        }
        if (addAppointmentStartField.getValue().isEqual(addAppointmentEndField.getValue())) {
            if (addAppointmentStartHoursField.getValue() > addAppointmentEndHoursField.getValue()) {
                valid = false;
            } if (addAppointmentStartHoursField.getValue() == addAppointmentEndHoursField.getValue()) {
                if (addAppointmentStartMinutesField.getValue() > addAppointmentEndMinutesField.getValue()) {
                    valid = false;
                }
            }
        }
        //check if the appointment overlaps with another appointment
        AppointmentDao ad = new AppointmentDao();
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        appointments = ad.getAllAppointments();
        LocalDateTime start = addAppointmentStartField.getValue().atTime(addAppointmentStartHoursField.getValue(), addAppointmentStartMinutesField.getValue());
        LocalDateTime end = addAppointmentEndField.getValue().atTime(addAppointmentEndHoursField.getValue(), addAppointmentEndMinutesField.getValue());
        //remove the appointment that is being edited from the list of appointments
        appointments.removeIf(appointment -> appointment.getAppointmentID() == Integer.parseInt(addAppointmentIDField.getText()));
        for (Appointment appointment : appointments) {
            if (appointment.getStart().isAfter(start) && appointment.getStart().isBefore(end)) {
                valid = false;
                //check locale and alert
                if (Locale.getDefault().getLanguage().equals("en")) {
                    Alerts.Alert("Error", "Error", "The appointment overlaps with another appointment");
                } else if (Locale.getDefault().getLanguage().equals("fr")) {
                    Alerts.Alert("Erreur", "Erreur", "Le rendez-vous chevauche un autre rendez-vous");
                }
            }
            if (appointment.getEnd().isAfter(start) && appointment.getEnd().isBefore(end)) {
                valid = false;
                //check locale and alert
                if (Locale.getDefault().getLanguage().equals("en")) {
                    Alerts.Alert("Error", "Error", "The appointment overlaps with another appointment");
                } else if (Locale.getDefault().getLanguage().equals("fr")) {
                    Alerts.Alert("Erreur", "Erreur", "Le rendez-vous chevauche un autre rendez-vous");
                }
            }
            if (appointment.getStart().isEqual(start) || appointment.getEnd().isEqual(end)) {
                //check locale and alert
                if (Locale.getDefault().getLanguage().equals("en")) {
                    Alerts.Alert("Error", "Error", "The appointment overlaps with another appointment");
                } else if (Locale.getDefault().getLanguage().equals("fr")) {
                    Alerts.Alert("Erreur", "Erreur", "Le rendez-vous chevauche un autre rendez-vous");
                }


                valid = false;
            }
            }
        //check if the appointment is within business hours
        ZoneId zoneID = zoneID = ZoneId.of("America/New_York");
        //convert time to zoneID time
        ZonedDateTime zdt = ZonedDateTime.of(start, zoneID);
        if ( zdt.getHour() < 8 || zdt.getHour() > 17) {
            //check locale and alert
            valid = false;
            if (Locale.getDefault().getLanguage().equals("en")) {
                Alerts.Alert("Error", "Error", "The appointment is not within business hours");
            } else if (Locale.getDefault().getLanguage().equals("fr")) {
                Alerts.Alert("Erreur", "Erreur", "Le rendez-vous n'est pas dans les heures de bureau");
            }
        }

        return valid;
    }

    /**
     * my addAppointmentAddButtonClick method adds the appointment to the database
     */

    @FXML
    void addAppointmentAddButtonClick(ActionEvent event) throws SQLException {
        if (!checkValues()) { //if checkValues returns false, there is an error
            Alerts.Alert("Error", "Error", "Please fix the missing or invalid fields, did you fill out all the fields and is there no overlap with another appointment?");
        } else {
            //parse data from fields
            int appointmentID = Integer.parseInt(addAppointmentIDField.getText());
            String title = addAppointmentTitleField.getText();
            String description = addAppointmentDescriptionField.getText();
            String location = addAppointmentLocationField.getText();
            String type = addAppointmentTypeField.getText();
            System.out.println(addAppointmentStartField.getValue().toString());
            LocalDateTime start = addAppointmentStartField.getValue().atTime(addAppointmentStartHoursField.getValue(), addAppointmentStartMinutesField.getValue());
            System.out.println(start);
            LocalDateTime end = addAppointmentEndField.getValue().atTime(addAppointmentEndHoursField.getValue(), addAppointmentEndMinutesField.getValue());
            //get customer id
            CustomerDao cd = new CustomerDao();
            int customerID = cd.getCustomerID(addAppointmentCustomerDropDown.getValue());
            //get user id
            UserDao ud = new UserDao();
            User userSelection = addAppointmentUserDropDown.getValue();
            System.out.println(userSelection);
            int userID = ud.getUserID(userSelection.getUserName());
            //get contact id
            ContactDao cod = new ContactDao();
            int contactID = cod.getContactID(addAppointmentContactDropDown.getValue());
            //get creation date
            LocalDate creationDate = java.time.LocalDate.now();
            //get current user
            String currentUser = loginController.getCurrentUser().getUserName();
            String createdBy = currentUser;
            LocalDate lastUpdate = java.time.LocalDate.now();
            String lastUpdatedBy = currentUser;
            //create appointment object
            Appointment appointment = new Appointment(appointmentID, title, description, location, type, start, end, customerID, userID, contactID, creationDate.atStartOfDay(), createdBy, lastUpdate.atStartOfDay(), lastUpdatedBy);

            System.out.println(appointment.getUserID());
            //try adding appointment to database, if it fails try updating it
            AppointmentDao ad = new AppointmentDao();
            //check if appointment is an update or a new appointment
            if (ad.appointmentExists(appointmentID)) {
                ad.updateAppointment(appointment);
            } else {
                System.out.println(userID);
                ad.addAppointment(appointment);
            }
            //return to tables view
            try {
                System.out.println("Loading tables view");
                FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.tablesController.class.getResource("/com/david/software2/views/tablesView.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Tables");
                stage.setX(stage.getX() - 270);
                stage.setY(stage.getY() - 100);
                stage.setScene(scene);
                stage.show();
            } catch (
                    Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * the addAppointmentCancelButtonClick method returns to the tables view
     */
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

    /**
     * the updateAppointmentPassData method passes data from the tables view to the add appointment view
     */
    void updateAppointmentPassData(Appointment appointment) throws SQLException {
        addAppointmentIDField.setText(String.valueOf(appointment.getAppointmentID()));
        addAppointmentTitleField.setText(appointment.getTitle());
        addAppointmentDescriptionField.setText(appointment.getDescription());
        addAppointmentLocationField.setText(appointment.getLocation());
        addAppointmentTypeField.setText(appointment.getType());
        LocalDate localDate = appointment.getStart().toLocalDate();
        addAppointmentStartField.setValue(localDate);
        LocalDate localDateEnd= appointment.getEnd().toLocalDate();
        addAppointmentEndField.setValue(localDateEnd);
        addAppointmentStartHoursField.getValueFactory().setValue(appointment.getStart().getHour());
        addAppointmentStartMinutesField.getValueFactory().setValue(appointment.getStart().getMinute());
        addAppointmentEndHoursField.getValueFactory().setValue(appointment.getEnd().getHour());
        addAppointmentEndMinutesField.getValueFactory().setValue(appointment.getEnd().getMinute());
        addAppointmentCustomerDropDown.setValue(appointment.getCustomerName());
        UserDao ud = new UserDao();
        System.out.println("test");
        int userID = appointment.getUserID();
        System.out.println("User ID: " + userID);
        User passedUser = ud.getUser(userID);
        addAppointmentUserDropDown.setValue(passedUser);
        addAppointmentContactDropDown.setValue(appointment.getContactName());
        addAppointmentAddButton.setText("Update");
    }

}
