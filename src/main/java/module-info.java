module com.application.projectmanager {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
            requires net.synedra.validatorfx;
            requires org.kordamp.ikonli.javafx;
            requires org.kordamp.bootstrapfx.core;
        
    opens com.application.projectmanager to javafx.fxml;
    exports com.application.projectmanager.Controllers;
    opens com.application.projectmanager.Controllers to javafx.fxml;
    exports com.application.projectmanager.Classes;
    opens com.application.projectmanager.Classes to javafx.fxml;
}