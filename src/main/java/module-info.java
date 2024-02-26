module application.segreteriauniprova {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;

    opens application to javafx.fxml;
    exports application;
    exports application.Controllers;
    exports application.Controllers.Secretary;
    exports application.Controllers.Student;
    exports application.Controllers.Teacher;
    opens application.Controllers to javafx.fxml;
    opens application.Controllers.Secretary to javafx.fxml;
    opens application.Controllers.Student to javafx.fxml;
    opens application.Controllers.Teacher to javafx.fxml;
    opens application.Models to javafx.base;
    exports application.Strategy;
    opens application.Strategy to javafx.fxml;
}