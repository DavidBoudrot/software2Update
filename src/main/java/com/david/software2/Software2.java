package com.david.software2;

import com.david.software2.controllers.loginController;
import com.david.software2.helper.JDBC;
import com.david.software2.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App that uses the MVC design pattern.
 * Project created for C195 Software II
 * This project is a scheduling application that allows users to make appointments with customers.
 * The application allows users to add, update, and delete customers and appointments.
 * It uses a database to store the data with MySQL.
 * DAO classes are used to make queries and updates to the database.
 */
public class Software2 extends Application {
    /**
     * Start method for the application
     * @param stage
     * @throws IOException
     */

    public Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        Locale locale = Locale.getDefault();
        FXMLLoader fxmlLoader = new FXMLLoader(loginController.class.getResource("/com/david/software2/views/loginView.fxml"), ResourceBundle.getBundle("com/david/software2/bundles/lang", locale));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        JDBC.openConnection();
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void stop() {
        JDBC.closeConnection();
    }
    public static void main(String[] args) {
        launch();
    }
}