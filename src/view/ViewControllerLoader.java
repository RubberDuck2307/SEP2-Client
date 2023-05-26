package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import model.Model;
import viewmodel.ViewModel;
import viewmodel.ViewModelFactory;

import java.util.HashMap;

public class ViewControllerLoader {

    private final HashMap<String, ViewController> loadedViewControllers;
    private final ViewModelFactory viewModelFactory;
    private final ViewHandler viewHandler;
    public ViewControllerLoader(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        loadedViewControllers = new HashMap<>();
        this.viewHandler = viewHandler;
    }

    public Region loadViewController(String id) {
        Region root;
        switch (id) {
            case "login" -> {
                root = loadViewController(viewModelFactory.getLoginViewModel(), "FXML/LoginView.fxml");
            }
            case "projectManagerHomePage" -> {
                root = loadViewController(viewModelFactory.getProjectManagerHomeViewModel(), "FXML/ProjectManagerHomeView.fxml");
            }
            case "hrHomePage" -> {
                root = loadViewController(viewModelFactory.getHrHomeViewModel(), "FXML/HrHomeView.fxml");
            }
            case "mainManagerHomePage" -> {
                root = loadViewController(viewModelFactory.getMainManagerHomeViewModel(), "FXML/MainManagerHomeView.fxml");
            }
            case "workerHomePage" -> {
                root = loadViewController(viewModelFactory.getWorkerHomeViewModel(), "FXML/WorkerHomeView.fxml");
            }
            case "editProfile" -> {
                root = loadViewController(viewModelFactory.getEditProfileViewModel(), "FXML/EditProfileView.fxml");
            }
            case "hrAndMainManagerProfile" -> {
                root = loadViewController(viewModelFactory.getHrAndMainManagerProfileViewModel(), "FXML/HrAndMainManagerProfileView.fxml");
            }
            case "workerProfile" -> {
                root = loadViewController(viewModelFactory.getWorkerProfileViewModel(), "FXML/WorkerProfileView.fxml");
            }
            case "projects" -> {
                root = loadViewController(viewModelFactory.getProjectsViewModel(), "FXML/ProjectsView.fxml");
            }
            case "editTask" -> {
                root = loadViewController(viewModelFactory.getEditTaskViewModel(), "FXML/EditTaskView.fxml");
            }
            case "tasks" -> {
                root = loadViewController(viewModelFactory.getTasksViewModel(), "FXML/TasksView.fxml");
            }
            case "assignWorkersToTask" -> {
                root = loadViewController(viewModelFactory.getAssignWorkersToTaskViewModel(), "FXML/AssignWorkersToTaskView.fxml");
            }
            case "assignWorkersToProject" -> {
                root = loadViewController(viewModelFactory.getAssignWorkersToProjectViewModel(), "FXML/AssignEmployeesToProjectView.fxml");
            }
            case "assignWorkersToProjectManager" -> {
                root = loadViewController(viewModelFactory.getAssignWorkersToProjectManagerViewModel(), "FXML/AssignWorkersToProjectManagerView.fxml");
            }
            case "addTask" -> {
                root = loadViewController(viewModelFactory.getAddTaskViewModel(), "FXML/AddTaskToProjectView.fxml");
            }
            case "addProject" -> {
                root = loadViewController(
                        viewModelFactory.getAddProjectViewModel(), "FXML/AddProjectView.fxml");
            }
            case "createUserProfile" -> {
                root = loadViewController(viewModelFactory.getCreateUserProfileViewModel(), "FXML/CreateUserProfileView.fxml");
            }
            case "workers" -> {
                root = loadViewController(viewModelFactory.getWorkersViewModel(), "FXML/WorkersView.fxml");
            }
            case "projectManagerPage" -> {
                root = loadViewController(viewModelFactory.getProjectManagerProfileViewModel(), "FXML/ProjectManagerProfileView.fxml");
            }
            case "editProject" -> {
                root = loadViewController(viewModelFactory.getEditProjectViewModel(), "FXML/EditProjectView.fxml");
            }
            case "deleteTags" -> {
                root = loadViewController(viewModelFactory.getDeleteTagsViewModel(), "FXML/DeleteTagsView.fxml");
            }
            case "forgotPassword" -> {
                root = loadViewController(viewModelFactory.getForgotPasswordViewModel(), "FXML/ForgotPasswordView.fxml");
            }
            case "notes" -> {
                root = loadViewController(viewModelFactory.getNotesViewModel(), "FXML/NotesView.fxml");
            }
            default -> {
                throw new IllegalArgumentException("Unknown id: " + id);
            }
        }
        return root;
    }

    private Region loadViewController(ViewModel viewModel, String fxmlFile) {

        Region root = null;
        ViewController viewController = null;

        if (loadedViewControllers.containsKey(fxmlFile)) {
            viewController = loadedViewControllers.get(fxmlFile);
            viewController.reset();

        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                viewController = loader.getController();
                viewController.init(viewHandler, viewModel, root);
                loadedViewControllers.put(fxmlFile, viewController);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return viewController.getRoot();
    }

}
