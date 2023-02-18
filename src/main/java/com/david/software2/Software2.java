package com.david.software2;

import com.david.software2.controllers.loginController;
import com.david.software2.helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;


public class Software2 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(loginController.class.getResource("/com/david/software2/views/loginView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        // open up the database connection
        // if the connection fails, then exit the program
        // if the connection succeeds, then continue

        JDBC.openConnection();
        JDBC.closeConnection();


        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}