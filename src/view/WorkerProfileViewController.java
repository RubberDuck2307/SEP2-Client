package view;

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
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.ViewModel;
import viewmodel.WorkerView.ProjectManagerProfileViewModel;
import viewmodel.WorkerView.TasksTableForWorkerProfile;
import viewmodel.WorkerView.WorkerProfileViewModel;

public class WorkerProfileViewController implements ViewController
{
  @FXML public HBox projectHBox;
  @FXML public ImageView avatarPic;
  @FXML public Label employeeName;
  @FXML public Label employeeWorkingNumber;
  @FXML public ImageView avatarPicture;
  @FXML public Label workerName;
  @FXML public Label workerRole;
  @FXML public Label workerDateOfBirth;
  @FXML public Label workerPhoneNumber;
  @FXML public Label workerEmail;
  @FXML public Label workerManagers;

  @FXML public TableView<ProjectsTable> currentProjectsTable;
  @FXML public TableColumn<ProjectsTable, String> projectTitle;
  @FXML public TableColumn<ProjectsTable, String> projectDeadline;

  @FXML public TableView<TasksTableForWorkerProfile> taskTable;
  @FXML public TableColumn<TasksTableForWorkerProfile, String> taskTitle;
  @FXML public TableColumn <TasksTableForWorkerProfile, String> taskStatus;
  @FXML public TableColumn<TasksTableForWorkerProfile, String>  taskProjectName;
  private Region root;
  private WorkerProfileViewModel viewModel;
  private ViewHandler viewHandler;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (WorkerProfileViewModel) viewModel;
    this.viewModel.load();
    employeeName.textProperty()
        .bindBidirectional(this.viewModel.getEmployeeName());
    avatarPic.imageProperty()
        .bindBidirectional(this.viewModel.avatarPicProperty());
    employeeWorkingNumber.textProperty()
        .bindBidirectional(this.viewModel.getEmployeeWorkingNumber());

    workerName.textProperty()
        .bindBidirectional(this.viewModel.workerNameProperty());
    workerEmail.textProperty()
        .bindBidirectional(this.viewModel.workerEmailProperty());
    workerDateOfBirth.textProperty()
        .bindBidirectional(this.viewModel.workerDateOfBirthProperty());
    workerRole.textProperty()
        .bindBidirectional(this.viewModel.workerRoleProperty());
    workerPhoneNumber.textProperty()
        .bindBidirectional(this.viewModel.workerPhoneNumberProperty());
    workerManagers.textProperty().bindBidirectional(this.viewModel.workerManagersProperty());

    projectDeadline.setCellValueFactory(
        cellData -> cellData.getValue().deadlineProperty());
    projectTitle.setCellValueFactory(
        cellData -> cellData.getValue().titleProperty());
    currentProjectsTable.setItems(
        ((WorkerProfileViewModel) viewModel).getCurrentProjectsTableTable());


    taskTitle.setCellValueFactory(
        cellData -> cellData.getValue().getTitleProperty());
    taskStatus.setCellValueFactory(
        cellData -> cellData.getValue().getStatusProperty());
    taskProjectName.setCellValueFactory(
        cellData -> cellData.getValue().projectNameProperty());
    taskTable.setItems(
        ((WorkerProfileViewModel) viewModel).getTaskTable());


    if (((WorkerProfileViewModel) viewModel).isProjectManagerWoman())
    {
      avatarPicture.setImage(new Image("/icons/woman-avatar.png"));
      //avatarPic.setImage(new Image("/icons/woman-avatar.png"));
    }

    this.viewModel.employeePropertyProperty()
        .addListener((observable, oldValue, newValue) -> {
          setWindow(((Employee) newValue).getRole());
        });
    setWindow(this.viewModel.getEmployeeProperty().getRole());
  }

  @Override public Region getRoot()
  {
    return root;
  }

  @Override public void reset()
  {
    viewModel.load();
  }

  public void openWorkersView()
  {
    viewHandler.openView("workers");
  }

  public void openProjects()
  {
    viewHandler.openView("projects");
  }

  public void goBackButton()
  {
    viewHandler.openLastWindow();
  }

  public void openHome()
  {
    EmployeeRole role = this.viewModel.getEmployeeProperty().getRole();
    switch (role) {
      case WORKER -> {
        viewHandler.openView("workerHomePage");
      }
      case HR -> {
        viewHandler.openView("home");
      }
      case PROJECT_MANAGER -> {
        viewHandler.openView("home");
      }
      case MAIN_MANAGER -> {
        viewHandler.openView("home");
      }
    }
  }

  private void setWindow(EmployeeRole employeeRole)
  {
    switch (employeeRole)
    {
      case WORKER ->
      {
        projectHBox.setVisible(true);
        projectHBox.setManaged(true);
      }
      case HR ->
      {
        projectHBox.setVisible(false);
        projectHBox.setManaged(false);
      }
      case PROJECT_MANAGER ->
      {

        projectHBox.setVisible(true);
        projectHBox.setManaged(true);
      }
      case MAIN_MANAGER ->
      {
        projectHBox.setVisible(true);
        projectHBox.setManaged(true);
      }
    }

  }
}
