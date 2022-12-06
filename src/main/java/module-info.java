module com.jgomwal111.chronometer {
    requires java.xml.bind;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    opens com.jgomwal111.chronometer.connection to java.xml.bind;
    exports com.jgomwal111.chronometer.controller;
    opens com.jgomwal111.chronometer.controller to javafx.fxml;
    exports com.jgomwal111.chronometer;
    opens com.jgomwal111.chronometer to javafx.fxml;
}