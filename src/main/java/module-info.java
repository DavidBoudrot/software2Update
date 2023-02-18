module com.david.software2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.david.software2 to javafx.fxml;
    exports com.david.software2;
    exports com.david.software2.controllers;
    opens com.david.software2.controllers to javafx.fxml;
}