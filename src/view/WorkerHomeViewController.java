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
import viewmodel.WorkerView.WorkerHomeViewModel;
import viewmodel.WorkerView.WorkerProfileViewModel;

public class WorkerHomeViewController implements ViewController
{
  @FXML public HBox projectHBox;
  @FXML public ImageView avatarPic;
  @FXML public Label employeeName;
  @FXML public Label employeeWorkingNumber;
  @FXML public Label workerName;

  @FXML public TableView<ProjectsTable> currentProjectsTable;
  @FXML public TableColumn<ProjectsTable, String> projectTitle;
  @FXML public TableColumn<ProjectsTable, String> projectDeadline;

  @FXML public TableView<TasksTableForWorkerProfile> taskTable;
  @FXML public TableColumn<TasksTableForWorkerProfile, String> taskTitle;
  @FXML public TableColumn <TasksTableForWorkerProfile, String> taskStatus;
  @FXML public TableColumn<TasksTableForWorkerProfile, String>  taskProjectName;
  private Region root;
  private WorkerHomeViewModel viewModel;
  private ViewHandler viewHandler;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (WorkerHomeViewModel) viewModel;
    this.viewModel.load();
    employeeName.textProperty()
        .bindBidirectional(this.viewModel.getEmployeeName());
    employeeWorkingNumber.textProperty()
        .bindBidirectional(this.viewModel.getEmployeeWorkingNumber());
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
    viewHandler.openView("home");
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
  public void logOut()
  {
    viewHandler.openView("login");
  }
}
