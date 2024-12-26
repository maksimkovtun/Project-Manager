package com.application.projectmanager.Controllers;

import com.application.projectmanager.DAO.ProjectsDAO;
import com.application.projectmanager.Entity.ProjectsEntity;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class editProjectController {
    public TextField deadLineTextField;
    public TextArea describeTextArea;
    public TextField nameTextField;
    public Label infoLabel;
    private ProjectsEntity project;
    private ProjectsDAO projectsDAO = new ProjectsDAO();
    public void setProject(ProjectsEntity project) {
        this.project = project;
        nameTextField.setText(project.getProjectName());
        deadLineTextField.setText(project.getProjectDeadLine());
        describeTextArea.setText(project.getProjectDescribe());
    }
    public void onUpdateButtonClick(ActionEvent event) {
        if (nameTextField.getText().isEmpty() || deadLineTextField.getText().isEmpty()) {
            infoLabel.setText("Название и срок сдачи обязательны.");
            return;
        }
        project.setProjectName(nameTextField.getText());
        project.setProjectDeadLine(deadLineTextField.getText());
        project.setProjectDescribe(describeTextArea.getText());
        projectsDAO.updateProject(project);
        ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
    }
}
