package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.EditTaskViewModel;
import viewmodel.ViewModel;
import viewmodel.ViewModelFactory;

import javax.swing.text.View;
import java.util.HashMap;

public class ViewHandler {
    private Scene currentScene;
    private Stage primaryStage;

    private String lastPageId;
    private String currentPageId;
    private ViewModelFactory viewModelFactory;
    private ViewController viewController;
    private HashMap<String, ViewController> loadedViewControllers;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        loadedViewControllers = new HashMap<>();
        this.viewModelFactory = viewModelFactory;
        currentScene = new Scene(new Region());
        lastPageId = "";
        currentPageId = "login";
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openView("login");
    }

    public void openView(String id) {
        Region root = null;
        switch (id) {
            case "login": {
                root = loadViewController(viewModelFactory.getLoginViewModel(), "LoginView.fxml");
                break;
            }
            case "projects": {
                root = loadViewController(viewModelFactory.getProjectsViewModel(), "ProjectsView.fxml");
                break;
            }
            case "editTask": {
                root = loadViewController(viewModelFactory.getEditTaskViewModel(), "EditTaskView.fxml");
                break;
            }
            case "tasks": {
                root = loadViewController(viewModelFactory.getTasksViewModel(), "TasksView.fxml");
                break;
            }
            case "assignWorkersToTask": {
                root = loadViewController(viewModelFactory.getAssignWorkersToTaskViewModel(), "AssignWorkersToTaskView.fxml");
                break;
            }
            case "assignWorkersToProject": {
                root = loadViewController(viewModelFactory.getAssignWorkersToProjectViewModel(), "AssignEmployeesToProjectView.fxml");
                break;
            }
            case "assignWorkersToProjectManager": {
                root = loadViewController(viewModelFactory.getAssignWorkersToProjectManagerViewModel(), "AssignWorkersToProjectManagerView.fxml");
                break;
            }
            case "addTask": {
                root = loadViewController(viewModelFactory.getAddTaskViewModel(), "AddTaskToProjectView.fxml");
                break;
            }
            case "addProject": {
                root = loadViewController(
                        viewModelFactory.getAddProjectViewModel(), "AddProjectView.fxml");
                break;
            }
            case "createUserProfile": {
                root = loadViewController(viewModelFactory.getCreateUserProfileViewModel(), "CreateUserProfileView.fxml");
                break;
            }
            case "workers": {
                root = loadViewController(viewModelFactory.getWorkersViewModel(), "WorkersView.fxml");
                break;
            }
            case "projectManagerPage": {
                root = loadViewController(viewModelFactory.getProjectManagerProfileViewModel(), "ProjectManagerProfileView.fxml");
                break;
            }
            case "home": {
                root = loadViewController(viewModelFactory.getHomeViewModel(), "HomeView.fxml");
                break;
            }
            case "editProject": {
                root = loadViewController(viewModelFactory.getEditProjectViewModel(), "EditProjectView.fxml");
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown id: " + id);
            }
        }
        lastPageId = currentPageId;
        currentPageId = id;
        currentScene.setRoot(root);
        String title = "";
        if (root.getUserData() != null) {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }

    private Region loadViewController(ViewModel viewModel, String fxmlFile) {

        Region root = null;

        if (loadedViewControllers.containsKey(fxmlFile)) {
            viewController = loadedViewControllers.get(fxmlFile);
            viewController.reset();

        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                viewController = loader.getController();
                viewController.init(this, viewModel, root);
                loadedViewControllers.put(fxmlFile, viewController);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return viewController.getRoot();
    }

    public void openLastWindow() {
        openView(lastPageId);
    }
}
