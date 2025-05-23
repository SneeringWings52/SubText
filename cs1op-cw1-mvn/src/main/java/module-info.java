module com.cw1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.cw1 to javafx.fxml;
    exports com.cw1;
}
