module com.david.software2 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.david.software2 to javafx.fxml;
    exports com.david.software2;
}