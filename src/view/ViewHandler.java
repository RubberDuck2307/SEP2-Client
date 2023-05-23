package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModel;
import viewmodel.ViewModelFactory;

import java.util.HashMap;

public class ViewHandler {
    private Scene currentScene;
    private Stage primaryStage;

    private ViewController currentViewController;

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
                root = loadViewController(viewModelFactory.getLoginViewModel(), "FXML/LoginView.fxml");
                break;
            }
            case "projectManagerHomePage": {
                root = loadViewController(viewModelFactory.getProjectManagerHomeViewModel(), "FXML/ProjectManagerHomeView.fxml");
                break;
            }
            case "hrHomePage": {
                root = loadViewController(viewModelFactory.getHrHomeViewModel(), "FXML/HrHomeView.fxml");
                break;
            }
            case "mainManagerHomePage": {
                root = loadViewController(viewModelFactory.getMainManagerHomeViewModel(), "FXML/MainManagerHomeView.fxml");
                break;
            }
            case "workerHomePage": {
                root = loadViewController(viewModelFactory.getWorkerHomeViewModel(), "FXML/WorkerHomeView.fxml");
                break;
            }
            case "editProfile": {
                root = loadViewController(viewModelFactory.getEditProfileViewModel(), "FXML/EditProfileView.fxml");
                break;
            }
            case "hrAndMainManagerProfile": {
                root = loadViewController(viewModelFactory.getHrAndMainManagerProfileViewModel(), "FXML/HrAndMainManagerProfileView.fxml");
                break;
            }
            case "workerProfile": {
                root = loadViewController(viewModelFactory.getWorkerProfileViewModel(), "FXML/WorkerProfileView.fxml");
                break;
            }
            case "projects": {
                root = loadViewController(viewModelFactory.getProjectsViewModel(), "FXML/ProjectsView.fxml");
                break;
            }
            case "editTask": {
                root = loadViewController(viewModelFactory.getEditTaskViewModel(), "FXML/EditTaskView.fxml");
                break;
            }
            case "tasks": {
                root = loadViewController(viewModelFactory.getTasksViewModel(), "FXML/TasksView.fxml");
                break;
            }
            case "assignWorkersToTask": {
                root = loadViewController(viewModelFactory.getAssignWorkersToTaskViewModel(), "FXML/AssignWorkersToTaskView.fxml");
                break;
            }
            case "assignWorkersToProject": {
                root = loadViewController(viewModelFactory.getAssignWorkersToProjectViewModel(), "FXML/AssignEmployeesToProjectView.fxml");
                break;
            }
            case "assignWorkersToProjectManager": {
                root = loadViewController(viewModelFactory.getAssignWorkersToProjectManagerViewModel(), "FXML/AssignWorkersToProjectManagerView.fxml");
                break;
            }
            case "addTask": {
                root = loadViewController(viewModelFactory.getAddTaskViewModel(), "FXML/AddTaskToProjectView.fxml");
                break;
            }
            case "addProject": {
                root = loadViewController(
                        viewModelFactory.getAddProjectViewModel(), "FXML/AddProjectView.fxml");
                break;
            }
            case "createUserProfile": {
                root = loadViewController(viewModelFactory.getCreateUserProfileViewModel(), "FXML/CreateUserProfileView.fxml");
                break;
            }
            case "workers": {
                root = loadViewController(viewModelFactory.getWorkersViewModel(), "FXML/WorkersView.fxml");
                break;
            }
            case "projectManagerPage": {
                root = loadViewController(viewModelFactory.getProjectManagerProfileViewModel(), "FXML/ProjectManagerProfileView.fxml");
                break;
            }
            case "home": {
                root = loadViewController(viewModelFactory.getHomeViewModel(), "FXML/HomeView.fxml");
                break;
            }
            case "editProject": {
                root = loadViewController(viewModelFactory.getEditProjectViewModel(), "FXML/EditProjectView.fxml");
                break;
            }
            case "deleteTags": {
                root = loadViewController(viewModelFactory.getDeleteTagsViewModel(), "FXML/DeleteTagsView.fxml");
                break;
            }
            case "forgotPassword":{
                root = loadViewController(viewModelFactory.getForgotPasswordViewModel(), "FXML/ForgotPasswordView.fxml");
                break;
            }
            case "notes": {
                root = loadViewController(viewModelFactory.getNotesViewModel(), "FXML/NotesView.fxml");
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
