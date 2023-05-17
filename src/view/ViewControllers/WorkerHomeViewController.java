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

public class WorkerHomeViewController implements ViewController
{
  @FXML public HBox projectHBox;
  @FXML public ImageView avatarPic;
  @FXML public Label employeeName;
  @FXML public Label employeeWorkingNumber;
  @FXML public Label workerName;


  @FXML public TableView<TasksTableForWorkerProfile> taskTable;
  @FXML public TableColumn<TasksTableForWorkerProfile, String> taskTitle;
  @FXML public TableColumn <TasksTableForWorkerProfile, String> taskStatus;
  @FXML public TableColumn<TasksTableForWorkerProfile, String>  taskProjectName;
  @FXML public TableView<NotificationTable> notificationTable;
  @FXML public TableColumn<NotificationTable, String> messageNotificationColumn;
  @FXML public TableColumn<NotificationTable, Button> deleteNotificationColumn;
   private ObservableList<NotificationTable> notificationTables;
  @FXML private TableColumn<TasksTableForWorkerProfile, String>  taskPriority;
  @FXML private Label workerName2;
  @FXML private Label workerRole;
  @FXML private Label workerDateOfBirth;
  @FXML private Label workerPhoneNumber;
  @FXML private Label workerEmail;
  @FXML private Label workerManagers;
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


    taskTitle.setCellValueFactory(
        cellData -> cellData.getValue().getTitleProperty());
    taskStatus.setCellValueFactory(
        cellData -> cellData.getValue().getStatusProperty());
    taskProjectName.setCellValueFactory(
        cellData -> cellData.getValue().projectNameProperty());
    taskPriority.setCellValueFactory(
        cellData -> cellData.getValue().priorityProperty());
    taskTable.setItems(
        ((WorkerHomeViewModel) viewModel).getTaskTable());

    messageNotificationColumn.setCellValueFactory(
        cellData -> cellData.getValue().messageProperty());
    notificationTable.setItems(((WorkerHomeViewModel) viewModel).getNotificationTable());

    PropertyValueFactory<NotificationTable, Button> button = new PropertyValueFactory("button");
    deleteNotificationColumn.setCellValueFactory(button);
    deleteNotificationColumn.setStyle("-fx-alignment: CENTER;");


    this.viewModel.employeePropertyProperty()
        .addListener((observable, oldValue, newValue) -> {
          setWindow(((Employee) newValue).getRole());
        });
    setWindow(this.viewModel.getEmployeeProperty().getRole());

    notificationTables = FXCollections.observableArrayList();
    fillInTasksTable();
    notificationTable.setItems(notificationTables);
  }
  private void fillInTasksTable() {
    notificationTables.clear();
    for (int i = 0; i < this.viewModel.getNotificationTable().size(); i++) {
      notificationTables.add(new NotificationTable(this.viewModel.getNotificationTable().get(i).getMessage()));
      Button button1 = new Button("");
      button1.setId("delete-button");

      int index =  i;
      button1.setOnAction(e -> {
        delete(viewModel.getNotificationTable().get(index).getMessage());
      });
      notificationTables.get(i).setButton(button1);
    }
  }
  private void delete(String message){
    viewModel.deleteNotification(message);
    reset();
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
