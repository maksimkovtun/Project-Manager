package com.application.projectmanager.Controllers;

import com.application.projectmanager.DAO.ProjectsDAO;
import com.application.projectmanager.DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class addProjectController {
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField deadLineTextField;
    @FXML
    public TextArea describeTextArea;
    @FXML
    public Label infoLabel;
    ProjectsDAO projectsDAO = new ProjectsDAO();
    UserDAO userDAO = new UserDAO();
    static String ProjectUsername = "";
    Integer userId = userDAO.getUserId(ProjectUsername);
    @FXML
    public void onAddButtonClick(ActionEvent event) {
        if(!Objects.equals(nameTextField.getText(), "")) {
            projectsDAO.createProject(nameTextField.getText(), deadLineTextField.getText(), describeTextArea.getText(), userId);
            ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
        }else{infoLabel.setText("Введите название.");}
    }
    public static void setUsername(String username){
        ProjectUsername = username;
    }
}
