package view.ViewControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import view.ViewController;
import view.ViewHandler;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.ProjectView.ProjectsViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkerView.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProjectManagerHomeViewController extends ViewControllerWithNavigationMenu
{
  @FXML public TableView<NotificationTable> notificationTable;
  @FXML public TableColumn<NotificationTable, String> messageNotificationColumn;
  @FXML public TableColumn<NotificationTable, Button> deleteNotificationColumn;

  @FXML private Label workerName2;

  @FXML private ImageView avatarPic;
  @FXML
  private HBox projectHBox;
  @FXML private Label managerName;
  @FXML private Label managerRole;
  @FXML private Label managerDateOfBirth;
  @FXML private Label managerPhoneNumber;
  @FXML private Label managerEmail;
  /*current project*/
  @FXML private TableView<ProjectsTable> currentProjectsTable;
  @FXML private TableColumn<ProjectsTable, String> projectTitle;
  @FXML private TableColumn<ProjectsTable, String> projectDeadline;



  /*assigned workers*/
  @FXML private TableView<viewmodel.WorkerView.WorkersTable> assignWorkersTable;
  @FXML private TableColumn<viewmodel.WorkerView.WorkersTable, String> workerNumber;
  @FXML private TableColumn<viewmodel.WorkerView.WorkersTable, String> workerName;
  @FXML private TableColumn <viewmodel.WorkerView.WorkersTable, String> workerEmail;

  @FXML private Label employeeName;
  @FXML private Label employeeWorkingNumber;

@FXML private ImageView bellImage;
  private Region root;
  private ProjectManagerHomeViewModel viewModel;
  private ViewHandler viewHandler;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (ProjectManagerHomeViewModel) viewModel;
    this.viewModel.load();
    super.init(this.viewModel, viewHandler, bellImage, avatarPic, employeeName, employeeWorkingNumber, projectHBox );

    bind();

    setWorkerTable();
    setProjectTable();
    setNotificationTable();



    workerName2.textProperty()
        .bindBidirectional(this.viewModel.workerName2Property());

    setWindow(this.viewModel.getEmployee().getRole());




  }

  private void setWorkerTable(){
    workerName.setCellValueFactory(
            cellData -> cellData.getValue().getNameProperty());
    workerNumber.setCellValueFactory(
            cellData -> cellData.getValue().getNumberProperty());
    workerEmail.setCellValueFactory(
            cellData -> cellData.getValue().getEmailProperty());
    assignWorkersTable.setItems( viewModel.getWorkersTable());
  }

  private void setNotificationTable(){
    messageNotificationColumn.setCellValueFactory(
            cellData -> cellData.getValue().textProperty());
    notificationTable.setItems( viewModel.getNotificationList());
  }

  private void setProjectTable(){
    projectDeadline.setCellValueFactory(
            cellData -> cellData.getValue().deadlineProperty());
    projectTitle.setCellValueFactory(
            cellData -> cellData.getValue().titleProperty());
    currentProjectsTable.setItems( viewModel.getCurrentProjectsTableTable());
  }

  private void bind(){
    managerName.textProperty().bindBidirectional(this.viewModel.managerNameProperty());
    managerEmail.textProperty().bindBidirectional(this.viewModel.managerEmailProperty());
    managerDateOfBirth.textProperty().bindBidirectional(this.viewModel.managerDateOfBirthProperty());
    managerRole.textProperty().bindBidirectional(this.viewModel.managerRoleProperty());
    managerPhoneNumber.textProperty().bindBidirectional(this.viewModel.managerPhoneNumberProperty());
  }


  private void delete(String message){
    viewModel.deleteNotification(message);
    reset();
  }

  @Override public Region getRoot()
  {
    return root;
  }

  @Override
  public void reset() {
    viewModel.reset();
    setWindow(this.viewModel.getEmployee().getRole());
  }


  public void assignedWorkerTableClick()
  {
  }


  protected void setWindow(EmployeeRole employeeRole) {
    super.setWindow(employeeRole);
  }

  @FXML public void assign(){
    viewHandler.openView("assignWorkersToProjectManager");
  }

  public void logOut()
  {
    viewHandler.openView("login");
  }
}
