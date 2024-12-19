package com.application.projectmanager.Controllers;

import com.application.projectmanager.Classes.projectManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class authorizationController {
    @FXML
    PasswordField passwordTextField;
    @FXML
    TextField loginTextField;

    @FXML
    protected void onAuthButtonClick(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        projectManager projectManager = new projectManager();
        projectManager.changeScene(stage, "/com/application/projectmanager/projectManager.fxml");
    }
}
