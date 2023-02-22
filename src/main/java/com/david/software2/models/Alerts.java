package com.david.software2.models;

import javafx.scene.control.Alert;

/**
 * Alert class for quick alerts
 */

public class Alerts {
    /**
     * I like making an alert class with a method to create a quick alert.
     * The Alert method takes in a title, header, and content.
     * @param title
     * @param header
     * @param content
     */
    public static void Alert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
