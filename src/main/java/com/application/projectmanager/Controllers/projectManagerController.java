package com.application.projectmanager.Controllers;

import com.application.projectmanager.Classes.projectManager;
import com.application.projectmanager.DAO.ProjectsDAO;
import com.application.projectmanager.DAO.UserDAO;
import com.application.projectmanager.Entity.ProjectsEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class projectManagerController implements Initializable {
    @FXML
    FlowPane projectsPane = new FlowPane();
    ProjectsDAO projectsDAO = new ProjectsDAO();
    UserDAO userDAO = new UserDAO();
    static String ProjectUsername = "";
    Integer userId;
    projectManager projectManager = new projectManager();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userId = userDAO.getUserId(ProjectUsername);
        setProjects();
    }
    @FXML
    public void addProject() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/projectmanager/addProject.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setTitle("Add project");
        newStage.setScene(new Scene(root));
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.showAndWait();
        setProjects();
    }
    @FXML
    public void relog(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        projectManager.changeScene(stage,"/com/application/projectmanager/authorization.fxml");
    }
    public static void setUsername(String username){
        ProjectUsername = username;
    }
    public void setProjects() {
        projectsPane.getChildren().clear();
        List<ProjectsEntity> projects = projectsDAO.projectsOutput(userId);
        for (ProjectsEntity project : projects) {
            VBox projectTile = createProjectTile(project);
            projectsPane.getChildren().add(projectTile);
        }
    }
    private VBox createProjectTile(ProjectsEntity project) {
        Label nameLabel = new Label(project.getProjectName());
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        String description = project.getProjectDescribe();
        String shortDescription = description.length() > 50 ? description.substring(0, 50) + "..." : description;
        Label descriptionLabel = new Label(shortDescription);
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555;");

        Label deadlineLabel = new Label("Deadline: " + project.getProjectDeadLine());
        deadlineLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #888;");

        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> {
            try {
                updateProject(project, e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteProject(project));
        
        VBox buttonBox = new VBox(5, editButton, deleteButton);
        buttonBox.setStyle("-fx-alignment: center;");
        
        VBox projectTile = new VBox(10, nameLabel, descriptionLabel, deadlineLabel, buttonBox);
        projectTile.setStyle("-fx-padding: 10; -fx-border-color: #ccc; -fx-border-radius: 5; -fx-background-color: #f9f9f9; -fx-background-radius: 5;");
        projectTile.setPrefWidth(150);
        projectTile.setMinHeight(150);

        return projectTile;
    }

    private void deleteProject(ProjectsEntity project) {
        projectsDAO.deleteProject(project);
        setProjects();
    }

    private void updateProject(ProjectsEntity project, javafx.event.ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/application/projectmanager/editProject.fxml"));
        Parent root = loader.load();
        editProjectController controller = loader.getController();
        controller.setProject(project);
        Stage modalStage = new Stage();
        modalStage.setTitle("Edit Project");
        modalStage.setScene(new Scene(root));
        modalStage.initModality(Modality.APPLICATION_MODAL);
        Stage parentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        modalStage.initOwner(parentStage);
        modalStage.showAndWait();
        setProjects();
    }
}