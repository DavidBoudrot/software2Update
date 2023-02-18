package com.david.software2.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class loginController {

    @FXML
    private Text LocaleText;

    @FXML
    private ChoiceBox<?> loginLanguageSelect;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private TextField loginUsernameField;

    public void setLocale(String locale) {
        LocaleText.setText(locale);
    }

    public void loginLoginButtonClick(ActionEvent actionEvent) throws Exception {
        //try username and password
//if correct, go to tables view
//if incorrect, display error message

        try { loginUsernameField.getText();
            //go to tables view
        } catch (Exception e) {
            //display error message
        }

        try { loginPasswordField.getText();
            //go to tables view
        } catch (Exception e) {
            //display error message
        }

        if (loginUsernameField.getText().equals("test") && loginPasswordField.getText().equals("test")) {
            //switch scene to tables view
            FXMLLoader fxmlLoader = new FXMLLoader(com.david.software2.controllers.tablesController.class.getResource("/com/david/software2/views/tablesView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Tables");
            stage.setX(stage.getX() - 270);
            stage.setY(stage.getY() - 100);

            stage.setScene(scene);

            stage.show();


        } else {
            //display error message
        }

    }



}


