module com.example.ex_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.ex_3 to javafx.fxml;
    exports com.example.ex_3;
}