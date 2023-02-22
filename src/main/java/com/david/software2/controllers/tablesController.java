package com.david.software2.controllers;

import com.david.software2.daos.AppointmentDao;
import com.david.software2.daos.CustomerDao;
import com.david.software2.models.User;
import com.david.software2.models.Alerts;
import com.david.software2.models.Appointment;
import com.david.software2.models.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Optional;

import static org.graalvm.compiler.options.OptionType.User;

public class tablesController {

    @FXML
    private Button tablesAddAppointmentButton;

    @FXML
    private Button tablesAddCustomerButton;

    @FXML
    private RadioButton tablesAllButton;

    @FXML
    private TableView<Appointment> tablesAppointmentTable;

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
    private TableView<Customer> tablesCustomerTable;

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
    private TableColumn<Customer, String> tablesCustomerTableStateColumn;

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
    private ObservableList<Customer> allCustomers;

    /**
     * This method initializes the tablesController
     * It gets the current user and checks if they have an appointment within 15 minutes
     * If they do, it alerts them
     * It also sets the language to french if the user's locale is french
     * It populates the customer table
     * It populates the appointment table
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        //alert user if they have an appointment within 15 minutes
        User currentUser = loginController.getCurrentUser();
        if (AppointmentDao.getAppointmentWithin15Minutes(currentUser)) {

            if (Locale.getDefault().getLanguage().equals("fr")) {
                Alerts.Alert("Rendez-vous dans 15 minutes!", "Rendez-vous dans 15 minutes!", "Rendez-vous dans 15 minutes!");
            } else {
                Alerts.Alert("Appointment in 15 minutes!", "Appointment in 15 minutes!", "Appointment in 15 minutes!");
            }
        }

        if (Locale.getDefault().getLanguage().equals("fr")) {
            tablesSchedulingText.setText("Planification");
            tablesSchedulingText.setStyle("-fx-font-size: 30px;");
            tablesCustomerText.setText("Client");
            tablesAppointmentText.setText("Rendez-vous");
            tablesAddCustomerButton.setText("Ajouter un client");
            tablesUpdateCustomerButton.setText("Mettre à jour le client");
            tablesDeleteCustomerButton.setText("Supprimer le client");
            tablesAddAppointmentButton.setText("Ajouter un rendez-vous");
            tablesUpdateAppointmentButton.setText("Mettre à jour le rendez-vous");
            tablesDeleteAppointmentButton.setText("Supprimer le rendez-vous");
            tablesReportButton.setText("Rapport");
            tablesLogoutButton.setText("Se déconnecter");
            tablesAllButton.setText("Tout");
            tablesWeekButton.setText("Semaine");
            tablesMonthButton.setText("Mois");
            tablesCustomerTableIDColumn.setText("ID");
            tablesCustomerTableNameColumn.setText("Nom");
            tablesCustomerTableAddressColumn.setText("Adresse");
            tablesCustomerTablePostalCodeColumn.setText("Code postal");
            tablesCustomerTableStateColumn.setText("État");
            tablesCustomerTablePhoneNumberColumn.setText("Numéro de téléphone");
            tablesAppointmentTableIDColumn.setText("ID");
            tablesAppointmentTableTitleColumn.setText("Titre");
            tablesAppointmentTableDescriptionColumn.setText("La description");
            tablesAppointmentTableLocationColumn.setText("Emplacement");
            tablesAppointmentTableTypeColumn.setText("Type");
            tablesAppointmentTableStartColumn.setText("Début");
            tablesAppointmentTableEndColumn.setText("Fin");
            tablesAppointmentTableContactColumn.setText("Contact");
            tablesAppointmentTableCustomerIDColumn.setText("ID du client");
            tablesAppointmentTableUserIDColumn.setText("ID de l'utilisateur");
        } else {

        }

        CustomerDao cd = new CustomerDao();
        ObservableList<Customer> allCustomers = cd.getAllCustomers();
        //populate the table
        tablesCustomerTable.setItems(cd.getAllCustomers());
        tablesCustomerTableAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        tablesCustomerTableIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        tablesCustomerTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tablesCustomerTablePhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tablesCustomerTablePostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        //get the division name from the division id associated with the customer
        tablesCustomerTableStateColumn.setCellValueFactory(cellData -> {
            int divisionID = cellData.getValue().getDivisionID();
            String divisionName = null;
            try {
                divisionName = cd.getDivisionName(divisionID).toString();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return new SimpleStringProperty(divisionName);
        });

        //populate the appointment table
        AppointmentDao ad = new AppointmentDao();
        ObservableList<Appointment> allAppointments = ad.getAllAppointments();
        tablesAppointmentTable.setItems(ad.getAllAppointments());
        tablesAppointmentTableIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        tablesAppointmentTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        tablesAppointmentTableDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        tablesAppointmentTableLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        tablesAppointmentTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        tablesAppointmentTableStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        tablesAppointmentTableEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        tablesAppointmentTableCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        tablesAppointmentTableUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        tablesAppointmentTableContactColumn.setCellValueFactory(new PropertyValueFactory<>("ContactID"));

        //set the date picker to today's date
        tablesAppointmentsDateField.setValue(LocalDate.now());


    }


    /**
     *This method switched to the add appointment scene.
     */
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

    /**
     * This method switches to the add customer scene.
     */

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


    /**
     * This method gets all appointments and populates the table.
     */
    @FXML
    void tablesAllButtonClick(ActionEvent event) throws SQLException {
        //get all appointments
        AppointmentDao ad = new AppointmentDao();
        tablesAppointmentTable.setItems(ad.getAllAppointments());
        tablesAppointmentTableIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        tablesAppointmentTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        tablesAppointmentTableDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        tablesAppointmentTableLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        tablesAppointmentTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        tablesAppointmentTableStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        tablesAppointmentTableEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        tablesAppointmentTableCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        tablesAppointmentTableUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        tablesAppointmentTableContactColumn.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
        tablesAppointmentTable.refresh();


    }


    /**
     * This method could be used some day but does not do anything.
     * It is called when the user clicks on the date picker.
     */

    @FXML
    void tablesAppointmentsDateFieldClick(ActionEvent event) {

    }


    /**
     * This method does some logic checks and then deletes the selected appointment.
     * @param event
     * @throws SQLException
     */
    @FXML
    void tablesDeleteButtonClick(ActionEvent event) throws SQLException {
        //check if appointment is selected
        //confirm if the user wants to delete the appointment with the appointment id and type
        //if yes, delete the appointment
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);


        try {
            Appointment selectedAppointment = tablesAppointmentTable.getSelectionModel().getSelectedItem();
            AppointmentDao ad = new AppointmentDao();
            ad.deleteAppointment(selectedAppointment.getAppointmentID());
        } catch (NullPointerException e) {


            //check user locale and set the language
            if (Locale.getDefault().getLanguage().equals("fr")) {
                Alerts.Alert("Aucun rendez-vous sélectionné", "Veuillez sélectionner un rendez-vous à supprimer", "Erreur");
            } else {
                Alerts.Alert("No appointment selected", "Please select an appointment to delete", "Error");
            }
        }


        if (Locale.getDefault().getLanguage().equals("fr")) {
            alert.setTitle("Supprimer le rendez-vous");
            alert.setHeaderText("Supprimer le rendez-vous");
            String appointmentType = tablesAppointmentTable.getSelectionModel().getSelectedItem().getType();
            String appointmentID = String.valueOf(tablesAppointmentTable.getSelectionModel().getSelectedItem().getAppointmentID());
            alert.setContentText("Êtes-vous sûr de vouloir supprimer ce rendez-vous? " + appointmentID + " " + appointmentType);
        } else {
            alert.setTitle("Delete Appointment");
            alert.setHeaderText("Delete Appointment");
            String appointmentType = tablesAppointmentTable.getSelectionModel().getSelectedItem().getType();
            String appointmentID = String.valueOf(tablesAppointmentTable.getSelectionModel().getSelectedItem().getAppointmentID());
            alert.setContentText("Are you sure you want to delete this appointment? " + appointmentID + " " + appointmentType);
        }
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            String appointmentType = tablesAppointmentTable.getSelectionModel().getSelectedItem().getType();
            String appointmentID = String.valueOf(tablesAppointmentTable.getSelectionModel().getSelectedItem().getAppointmentID());

            if (Locale.getDefault().getLanguage().equals("fr")) {
                Alerts.Alert("Rendez-vous supprimé", "Rendez-vous supprimé", "Rendez-vous supprimé" + appointmentID + " " + appointmentType);
            } else {
                Alerts.Alert("Appointment deleted", "Appointment deleted", "Deleted appointment" + appointmentID + " " + appointmentType);
            }
            Appointment selectedAppointment = tablesAppointmentTable.getSelectionModel().getSelectedItem();
            AppointmentDao ad = new AppointmentDao();
            ad.deleteAppointment(selectedAppointment.getAppointmentID());
            //refresh the table
            tablesAppointmentTable.setItems(ad.getAllAppointments());
            tablesAppointmentTable.refresh();
        } else {
        }


    }


    /**
     * This method is for deleting a customer.
     * It does some logic checks and then deletes the selected customer.
     * The main logic check is to see if the customer has any appointments.
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void tablesDeleteCustomerButtonClick(ActionEvent event) throws SQLException, IOException {
        boolean goodToGo = true;
        //My solution for stopping the confirmation alert from popping up when a customer has appointments and they have been alerted.
        //Before it showed both alerts.

        if (tablesCustomerTable.getSelectionModel().getSelectedItem() == null) {
            if (Locale.getDefault().getLanguage().equals("en")) {
                Alerts.Alert("No customer selected", "Please select a customer to delete", "Error");
            } else {
                Alerts.Alert("Aucun client sélectionné", "Veuillez sélectionner un client à supprimer", "Erreur");
            }
            //restart the method
            FXMLLoader fxmlLoader = new FXMLLoader(tablesController.class.getResource("/com/david/software2/views/tablesView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setX(stage.getX() + 270);
            stage.setY(stage.getY() + 100);
            stage.show();
        }

        //check if customer has any appointments
        CustomerDao cd = new CustomerDao();
        Customer selectedCustomer = tablesCustomerTable.getSelectionModel().getSelectedItem();
        if (cd.hasAppointments(selectedCustomer)) {
            if (Locale.getDefault().getLanguage().equals("fr")) {
                Alerts.Alert("Impossible de supprimer le client", "Le client a des rendez-vous", "Erreur");
                goodToGo = false;
                FXMLLoader fxmlLoader = new FXMLLoader(tablesController.class.getResource("/com/david/software2/views/tablesView.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setX(stage.getX() + 270);
                stage.setY(stage.getY() + 100);
                stage.show();
            } else {
                Alerts.Alert("Unable to delete customer", "Customer has appointments", "Error");
                goodToGo = false;
                FXMLLoader fxmlLoader = new FXMLLoader(tablesController.class.getResource("/com/david/software2/views/tablesView.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setX(stage.getX() + 270);
                stage.setY(stage.getY() + 100);
                stage.show();
            }

        }


        if (Locale.getDefault().getLanguage().equals("fr") && goodToGo) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer le client");
            alert.setHeaderText("Supprimer le client");
            String customerName = tablesCustomerTable.getSelectionModel().getSelectedItem().getCustomerName();
            String customerID = String.valueOf(tablesCustomerTable.getSelectionModel().getSelectedItem().getCustomerID());
            alert.setContentText("Êtes-vous sûr de vouloir supprimer ce client? " + customerID + " " + customerName);
        } else if (goodToGo) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Customer");
            alert.setHeaderText("Delete Customer");
            String customerName = tablesCustomerTable.getSelectionModel().getSelectedItem().getCustomerName();
            String customerID = String.valueOf(tablesCustomerTable.getSelectionModel().getSelectedItem().getCustomerID());
            alert.setContentText("Are you sure you want to delete this customer? " + customerID + " " + customerName);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    Alerts.Alert("Client supprimé", "Client supprimé", "Client supprimé" + customerID + " " + customerName);
                } else {
                    Alerts.Alert("Customer deleted", "Customer deleted", "Deleted customer" + customerID + " " + customerName);
                }
                cd.deleteCustomer(selectedCustomer.getCustomerID());
                tablesCustomerTable.setItems(cd.getAllCustomers());
                tablesCustomerTable.refresh();
            } else {
            }
        }

        //confirm if the user wants to delete the customer with the customer id and name
        //if yes, delete the customer

    }


    /**
     * This method is for logging out.
     * It switches the scene back to the login view.
     * @param event
     * @throws Exception
     */
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


    /**
     * This method sorts the appointments table by month.
     * @param event
     * @throws SQLException
     */
    @FXML
    void tablesMonthButtonClick(ActionEvent event) throws SQLException {
        //sort the appointment table by month
        AppointmentDao ad = new AppointmentDao();
        ObservableList<Appointment> allAppointments = ad.getAllAppointments();
        //get the current values from datepicker
        LocalDate currentdate = tablesAppointmentsDateField.getValue();
        //create a new date 30 days from the current date
        LocalDate targetDate = currentdate.plusDays(30);
        //create a new observable list to hold the appointments in the next 30 days
        ObservableList<Appointment> targetAppointments = FXCollections.observableArrayList();
        //create another list to hold ALL possible dates these appointments could be on
        ObservableList<LocalDate> possibleDates = FXCollections.observableArrayList();
        //find all possible days that are in the next 30 days of the current date
        for (int i = 0; i < 30; i++) {
            possibleDates.add(currentdate.plusDays(i));
        }
        //find appointments in the next 30 days
        for (Appointment appointment : allAppointments) {
            //get the start date of the appointment
            String appointmentDate = appointment.getStart().toString();
            //create substring of appointmentdate to remove the time
            String appointmentDateSubstring = appointmentDate.substring(0, 10);
            //if the appointment date is in the next 30 days, add it to the target appointments list
            if (possibleDates.contains(LocalDate.parse(appointmentDateSubstring))) {
                targetAppointments.add(appointment);
            } else {
            }
        }
        //clear the tables
        tablesAppointmentTable.getItems().clear();
        //populate the appointment table
        tablesAppointmentTable.setItems(targetAppointments);
        tablesAppointmentTableIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        tablesAppointmentTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        tablesAppointmentTableDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        tablesAppointmentTableLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        tablesAppointmentTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        tablesAppointmentTableStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        tablesAppointmentTableEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        tablesAppointmentTableCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        tablesAppointmentTableUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        tablesAppointmentTableContactColumn.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
        tablesAppointmentTable.refresh();


    }


    /**
     * This method switches to the reports view.
     * @param event
     * @throws Exception
     */
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


    /**
     * This method passes appointment data into the add appointment view.
     * @param event
     * @throws Exception
     */
    @FXML
    void tablesUpdateAppointmentButtonClick(ActionEvent event) throws Exception {
        //check if an appointment is selected
        if  (tablesAppointmentTable.getSelectionModel().getSelectedItem() == null) {
            if (Locale.getDefault().getLanguage().equals("fr")) {
                Alerts.Alert("Aucun rendez-vous sélectionné", "Aucun rendez-vous sélectionné", "Veuillez sélectionner un rendez-vous à mettre à jour.");
            } else {
                Alerts.Alert("No appointment selected", "No appointment selected", "Please select an appointment to update.");
            }
            return;
        } else {}
        //get selection
        Appointment selectedAppointment = tablesAppointmentTable.getSelectionModel().getSelectedItem();
        //load the add appointment view and pass the selected appointment
        FXMLLoader fxmlLoader = new FXMLLoader(tablesController.class.getResource("/com/david/software2/views/addAppointmentView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addAppointmentController addAppointmentController = fxmlLoader.getController();
        addAppointmentController.updateAppointmentPassData(selectedAppointment);
        stage.setTitle("Update Appointment");
        stage.setScene(scene);
        stage.setX(stage.getX() + 270);
        stage.setY(stage.getY() + 100);
        stage.show();
    }


    /**
     * This method passes customer data into the add customer view.
     * @param event
     * @throws SQLException
     */
    @FXML
    void tablesUpdateCustomerButtonClick(ActionEvent event) throws SQLException {
        //get the selected customer
        if (tablesCustomerTable.getSelectionModel().getSelectedItem() == null) {
            if (Locale.getDefault().getLanguage().equals("fr")) {
                Alerts.Alert("Aucun client sélectionné", "Aucun client sélectionné", "Veuillez sélectionner un client à mettre à jour.");
            } else {
                Alerts.Alert("No customer selected", "No customer selected", "Please select a customer to update.");
            }
            return;
        } else {}
        Customer selectedCustomer = tablesCustomerTable.getSelectionModel().getSelectedItem();
        //load the add customer view and pass the selected customer
        FXMLLoader fxmlLoader = new FXMLLoader(tablesController.class.getResource("/com/david/software2/views/addCustomerView.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Update Customer");
        stage.setScene(scene);
        stage.setX(stage.getX() + 270);
        stage.setY(stage.getY() + 100);
        stage.show();
        //pass the selected customer to the add customer controller
        addCustomerController controller = fxmlLoader.getController();
        controller.setCustomer(selectedCustomer);





    }


    /**
     * This method sorts the appointment table by week.
     * @param event
     * @throws Exception
     */

    @FXML
    void tablesWeekButtonClick(ActionEvent event) throws SQLException {
        //sort the appointment table by week
        AppointmentDao ad = new AppointmentDao();
        ObservableList<Appointment> allAppointments = ad.getAllAppointments();
        String currentweek = String.valueOf(LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()));
        System.out.println(currentweek);

        ObservableList<Appointment> targetAppointments = FXCollections.observableArrayList();
        //find appointments with desired week

        //get date from the date picker
        String currentdate = tablesAppointmentsDateField.getValue().toString();
        //get all potential strings for the current week
        String[] weekdates = new String[7];
        for (int i = 0; i < 7; i++) {
            weekdates[i] = String.valueOf(LocalDate.parse(currentdate).plusDays(i));
        }
        //find appointments with desired week
        for (Appointment a : allAppointments) {
            for (String s : weekdates) {
                if (a.getStart().toString().contains(s)) {
                    targetAppointments.add(a);
                } else {

                }
            }
        }
        //check if there is any search text
//        if (tablesAppointment)

        //clear the tables
        tablesAppointmentTable.getItems().clear();
        //populate the appointment table
        tablesAppointmentTable.setItems(targetAppointments);
        tablesAppointmentTableIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        tablesAppointmentTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        tablesAppointmentTableDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        tablesAppointmentTableLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        tablesAppointmentTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        tablesAppointmentTableStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        tablesAppointmentTableEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        tablesAppointmentTableCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        tablesAppointmentTableUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        tablesAppointmentTableContactColumn.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
        tablesAppointmentTable.refresh();

        }



    }

