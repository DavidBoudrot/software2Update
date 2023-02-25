package com.david.software2.controllers;

import com.david.software2.daos.UserDao;
import com.david.software2.models.Alerts;
import com.david.software2.models.LogUser;
import com.david.software2.models.User;
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

import javax.xml.stream.Location;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;


public class loginController {

    @FXML
    public Text passText;
    @FXML
    public Text Title;

    @FXML

    public Text userText;

    @FXML
    public Text locationText;

    @FXML
    private Text LocaleText;

    @FXML
    private static User currentUser;

    @FXML
    private ChoiceBox<String> loginLanguageSelect;

    @FXML
    private PasswordField loginPasswordField;


    @FXML
    private TextField loginUsernameField;
    @FXML
    private Button loginLoginButton;

    Stage stage;
    Parent root;
    Scene scene;

    private boolean firstTime;

    //set localetext to the current location


    /**
     * Init method for the login controller
     * Sets the choice box to the current language
     * Sets the text fields to the current language
     * Sets the locale text to the current location
     */

    @FXML
    public void initialize() throws IOException {
        firstTime = true;
        Locale locale = Locale.getDefault();
        setChoiceBox();
        LocaleText.setText(locale.getDisplayCountry());

        if (Locale.getDefault().getDisplayLanguage().equals("English")) {



        } else {
            //move the title to the left
            Title.setLayoutX(150);
            //move the location text to the left
            locationText.setLayoutX(315);


        }
    }


    /**
     * Sets the choice box up with the languages
     */
    public void setChoiceBox() {
        ObservableList<String> languages = FXCollections.observableArrayList("English", "Français");
        loginLanguageSelect.setItems(languages);
        loginLanguageSelect.setValue(Locale.getDefault().getDisplayLanguage());


    }


    /**
     * The method for when a language is selected from the combo box.
     * It will change the locale and set the current scenes text fields to the correct language
     */
    @FXML
    void loginLanguageSelectClick(ActionEvent actionEvent) throws IOException {
        if (firstTime) {
            firstTime = false;
            return;
        } else {

            String lang = loginLanguageSelect.getValue();
            Locale locale;
            if (lang.equals("English")) {
                locale = new Locale("en", "US");
                Locale.setDefault(locale);
            } else {
                locale = new Locale("fr", "FR");
                Locale.setDefault(locale);
            }
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(loginController.class.getResource("/com/david/software2/views/loginView.fxml"), ResourceBundle.getBundle("com/david/software2/bundles/lang", locale));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();

        }
    }


    /**
     * method for when the login button is clicked
     * It will check the username and password
     * If the username and password are correct it will log the user in
     * If the username and password are incorrect it will display an error
     * The error is different depending on the language
     */
    public void loginLoginButtonClick(ActionEvent actionEvent) throws Exception {
        //try username and password
//if correct, go to tables view
//if incorrect, display error message
        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();
        System.out.println(username);

        UserDao dao = new UserDao(); // create a new UserDao object
        User user = dao.getUser(username); // get the user from the database
        if (user != null) { // check if username exists
            if (password.equals(user.getPassword())) { // check if password is correct
                // go to tables view
                //use log class to log user login
                LocalDateTime currentTime = LocalDateTime.now();
                new LogUser(username, currentTime, true);
                currentUser = user;
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/david/software2/views/tablesView.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Tables");

                stage.show();

            } else {
                //if password is incorrect, display error message that is in the correct language
                Locale locale = Locale.getDefault();
                if (locale.getDisplayLanguage().equals("English")) {
                    Alerts.Alert("Error", "Incorrect Username or Password", "Please try again");
                } else {
                    Alerts.Alert("Erreur", "Nom d'utilisateur ou mot de passe incorrect", "Veuillez réessayer");
                }
                LogUser logUser = new LogUser(username, LocalDateTime.now(), false);
            }
        } else {
            Locale locale = Locale.getDefault();
            if (locale.getDisplayLanguage().equals("English")) {
                Alerts.Alert("Error", "Incorrect Username or Password", "Please try again");
            } else {
                Alerts.Alert("Erreur", "Nom d'utilisateur ou mot de passe incorrect", "Veuillez réessayer");
            }
            LogUser logUser = new LogUser(username, LocalDateTime.now(), false);
        }
    }

    /**
     * Method for getting the current user used throughout the program
     *
     * @return
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    protected ObservableList<String> translate(ObservableList<String> text, ResourceBundle bundle) {
        ObservableList<String> translatedBundle = FXCollections.observableArrayList();
        for (String string : text) {
            translatedBundle.add(bundle.getString(string));
        }
        return translatedBundle;

    }
}






