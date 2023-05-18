package view.ViewControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.Employee;
import model.EmployeeRole;
import view.ViewController;
import view.ViewHandler;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.ViewModel;
import viewmodel.WorkerView.ProjectManagerProfileViewModel;
import viewmodel.WorkerView.TasksTableForWorkerProfile;
import viewmodel.WorkerView.WorkerHomeViewModel;
import viewmodel.WorkerView.WorkerProfileViewModel;

public class WorkerHomeViewController extends ViewControllerWithNavigationMenu {
    @FXML
    public HBox projectHBox;
    @FXML
    public ImageView avatarPic;
    @FXML
    public Label employeeName;
    @FXML
    public Label employeeWorkingNumber;
    @FXML
    public Label workerName;
    @FXML
    public TableView<ProjectsTable> currentProjectsTable;
    @FXML
    public TableColumn<ProjectsTable, String> projectTitle;
    @FXML
    public TableColumn<ProjectsTable, String> projectDeadline;
    @FXML
    public TableView<TasksTableForWorkerProfile> taskTable;
    @FXML
    public TableColumn<TasksTableForWorkerProfile, String> taskTitle;
    @FXML
    public TableColumn<TasksTableForWorkerProfile, String> taskStatus;
    @FXML
    public TableColumn<TasksTableForWorkerProfile, String> taskProjectName;
    private Region root;
    private WorkerHomeViewModel viewModel;
    @FXML
    private ImageView bellImage;

    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel,
                     Region root) {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (WorkerHomeViewModel) viewModel;
        super.init(this.viewModel, viewHandler, bellImage, avatarPic, employeeName, employeeWorkingNumber, projectHBox);
        this.viewModel.load();

        workerName.textProperty()
                .bindBidirectional(this.viewModel.workerNameProperty());

        projectDeadline.setCellValueFactory(
                cellData -> cellData.getValue().deadlineProperty());
        projectTitle.setCellValueFactory(
                cellData -> cellData.getValue().titleProperty());
        currentProjectsTable.setItems(
                ((WorkerHomeViewModel) viewModel).getCurrentProjectsTableTable());


        taskTitle.setCellValueFactory(
                cellData -> cellData.getValue().getTitleProperty());
        taskStatus.setCellValueFactory(
                cellData -> cellData.getValue().getStatusProperty());
        taskProjectName.setCellValueFactory(
                cellData -> cellData.getValue().projectNameProperty());
        taskTable.setItems(
                ((WorkerHomeViewModel) viewModel).getTaskTable());

        setWindow(this.viewModel.getEmployee().getRole());
    }

    @Override
    public Region getRoot() {
        return root;
    }

    @Override
    public void reset() {
        viewModel.reset();
    }


    protected void setWindow(EmployeeRole employeeRole) {
        super.setWindow(employeeRole);

    }

    public void logOut() {
        viewHandler.openView("login");
    }
}
