package com.application.projectmanager.Controllers;

import com.application.projectmanager.Classes.projectManager;
import com.application.projectmanager.DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

public class authorizationController {
    @FXML
    public Label infoLabel;
    @FXML
    PasswordField passwordTextField;
    @FXML
    TextField loginTextField;
    UserDAO userDAO = new UserDAO();
    projectManager projectManager = new projectManager();
    @FXML
    protected void onAuthButtonClick(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        if(!Objects.equals(loginTextField.getText(), "") && !Objects.equals(passwordTextField.getText(), "")) {
            if(userDAO.checkAccount(loginTextField.getText(), passwordTextField.getText())){
                projectManager.changeScene(stage, "/com/application/projectmanager/projectManager.fxml");
            }else{infoLabel.setText("Ошибка логина или пароля.");}
        }else{infoLabel.setText("Введите логин и пароль.");}
    }
    @FXML
    public void onRegLinkClick(ActionEvent event) throws Exception{
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        projectManager.changeScene(stage, "/com/application/projectmanager/registration.fxml");
    }
}
