package com.application.projectmanager.Controllers;

import com.application.projectmanager.Classes.projectManager;
import com.application.projectmanager.DAO.UserDAO;
import com.application.projectmanager.Entity.UsersEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

public class registrationController {
    @FXML
    public Label infoLabel;
    @FXML
    PasswordField passwordTextField;
    @FXML
    TextField loginTextField;
    projectManager projectManager = new projectManager();
    Stage stage = new Stage();
    @FXML
    public void onRegButtonClick(ActionEvent event) throws Exception{
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Session session = projectManager.getSession();
        Transaction transaction = null;
        UserDAO userDAO = new UserDAO();
        UsersEntity user = new UsersEntity();

        if(!Objects.equals(loginTextField.getText(), "") && !Objects.equals(passwordTextField.getText(), "")) {
            if(!userDAO.checkUsername(loginTextField.getText())) {
                try {
                    transaction = session.beginTransaction();
                    user.createUser(loginTextField.getText(), BCrypt.hashpw(passwordTextField.getText(), BCrypt.gensalt()));
                    session.save(user);
                    transaction.commit();
                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                    e.printStackTrace();
                } finally {
                    projectManager.changeScene(stage, "/com/application/projectmanager/authorization.fxml");
                }
            }else{infoLabel.setText("Имя занято.");}
        }else{infoLabel.setText("Введите логин и пароль.");}
    }
    @FXML
    public void onAuthLinkClick(ActionEvent event) throws Exception{
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        projectManager.changeScene(stage, "/com/application/projectmanager/authorization.fxml");
    }
}
