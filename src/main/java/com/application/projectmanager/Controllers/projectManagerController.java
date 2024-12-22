package com.application.projectmanager.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class projectManagerController {
    public void addProject() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/projectmanager/addProject.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setTitle("Add project");
        newStage.setScene(new Scene(root));
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.show();
    }
}