package view.ViewControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.Employee;
import model.EmployeeRole;
import model.Tag;
import view.ViewController;
import view.ViewHandler;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.TaskView.TasksTable;
import viewmodel.ViewModel;
import viewmodel.WorkerView.*;

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
  @FXML public TableView<NotificationTable> notificationTable;
  @FXML public TableColumn<NotificationTable, String> messageNotificationColumn;
  @FXML private TableColumn<TasksTableForWorkerProfile, String>  taskPriority;
  @FXML private Label workerName2;
  @FXML private Label workerRole;
  @FXML private Label workerDateOfBirth;
  @FXML private Label workerPhoneNumber;
  @FXML private Label workerEmail;
  @FXML private Label workerManagers;
    @FXML
    private ImageView bellImage;

    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel,
                     Region root) {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (WorkerHomeViewModel) viewModel;
        this.viewModel.load();
        super.init(this.viewModel, viewHandler, bellImage, avatarPic, employeeName, employeeWorkingNumber, projectHBox);
        setTaskTable();

        bind();


      messageNotificationColumn.setCellValueFactory(
              cellData -> cellData.getValue().textProperty());
      notificationTable.setItems(((WorkerHomeViewModel) viewModel).getNotificationList());




        setWindow(this.viewModel.getEmployee().getRole());
    }


    private void setTaskTable(){
        taskTitle.setCellValueFactory(
                cellData -> cellData.getValue().getTitleProperty());
        taskStatus.setCellValueFactory(
                cellData -> cellData.getValue().getStatusProperty());
        taskProjectName.setCellValueFactory(
                cellData -> cellData.getValue().projectNameProperty());
        taskTable.setItems
                (this.viewModel.getTaskTable());
        taskPriority.setCellValueFactory(
                cellData -> cellData.getValue().priorityProperty());
    }
    private void bind(){
        workerName.textProperty()
                .bindBidirectional(this.viewModel.workerNameProperty());
        workerName2.textProperty()
                .bindBidirectional(this.viewModel.workerName2Property());
        workerEmail.textProperty()
                .bindBidirectional(this.viewModel.workerEmailProperty());
        workerDateOfBirth.textProperty()
                .bindBidirectional(this.viewModel.workerDateOfBirthProperty());
        workerRole.textProperty()
                .bindBidirectional(this.viewModel.workerRoleProperty());
        workerPhoneNumber.textProperty()
                .bindBidirectional(this.viewModel.workerPhoneNumberProperty());
        workerManagers.textProperty().bindBidirectional(this.viewModel.workerManagersProperty());
    }


  private void delete(String message){
    viewModel.deleteNotification(message);
    reset();
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
        viewModel.logOut();
        viewHandler.openView("login");
    }

    public void openNotes(){viewHandler.openView("notes");}

}
