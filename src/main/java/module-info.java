module com.lucar01.aeroporto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;

    requires mysql.connector.java;

    requires junit;
    requires org.junit.jupiter.api;

    opens com.lucar01.aeroporto to javafx.fxml;
    // opens controllers;
    // opens table;
    opens test.com.lucar01.aeroporto;

    exports com.lucar01.aeroporto;
    exports com.lucar01.aeroporto.table;
    opens com.lucar01.aeroporto.table to javafx.fxml;
}