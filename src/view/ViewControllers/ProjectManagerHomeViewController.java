package view.ViewControllers;

import javafx.event.ActionEvent;
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
import viewmodel.ProjectView.ProjectsViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkerView.ProjectManagerHomeViewModel;
import viewmodel.WorkerView.ProjectManagerProfileViewModel;
import viewmodel.WorkerView.WorkerProfileViewModel;
import viewmodel.WorkerView.WorkersViewModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProjectManagerHomeViewController implements ViewController
{
  //TODO link and viewhandler
  @FXML private Label workerName2;
  @FXML private Button backButton;
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
  @FXML private Button assignButton;

  @FXML private Label employeeName;
  @FXML private Label employeeWorkingNumber;
  @FXML private ImageView avatarPicture;

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
    employeeName.textProperty().bindBidirectional(this.viewModel.getEmployeeName());

    avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
    employeeWorkingNumber.textProperty().bindBidirectional(this.viewModel.getEmployeeWorkingNumber());
    managerName.textProperty().bindBidirectional(this.viewModel.managerNameProperty());
    managerEmail.textProperty().bindBidirectional(this.viewModel.managerEmailProperty());
    managerDateOfBirth.textProperty().bindBidirectional(this.viewModel.managerDateOfBirthProperty());
    managerRole.textProperty().bindBidirectional(this.viewModel.managerRoleProperty());
    managerPhoneNumber.textProperty().bindBidirectional(this.viewModel.managerPhoneNumberProperty());
    workerName.setCellValueFactory(
        cellData -> cellData.getValue().getNameProperty());
    workerNumber.setCellValueFactory(
        cellData -> cellData.getValue().getNumberProperty());
    workerEmail.setCellValueFactory(
        cellData -> cellData.getValue().getEmailProperty());
    assignWorkersTable.setItems(((ProjectManagerHomeViewModel) viewModel).getWorkersTable());

    projectDeadline.setCellValueFactory(
        cellData -> cellData.getValue().deadlineProperty());
    projectTitle.setCellValueFactory(
        cellData -> cellData.getValue().titleProperty());
    currentProjectsTable.setItems(((ProjectManagerHomeViewModel) viewModel).getCurrentProjectsTableTable());
    workerName2.textProperty()
        .bindBidirectional(this.viewModel.workerName2Property());
    isWoman();
    this.viewModel.employeePropertyProperty().addListener((observable, oldValue, newValue) -> {
      setWindow(((Employee) newValue).getRole());
    });
    setWindow(this.viewModel.getEmployeeProperty().getRole());
  }
  public void isWoman(){
    if (((ProjectManagerHomeViewModel) viewModel).isProjectManagerWoman())
    {
      avatarPicture.setImage(new Image("/icons/woman-avatar.png"));
      //avatarPic.setImage(new Image("/icons/woman-avatar.png"));
    }
    else{
      avatarPicture.setImage(new Image("/icons/man-avatar.png"));
    }
  }

  @Override public Region getRoot()
  {
    return root;
  }

  @Override
  public void reset() {
    isWoman();
    viewModel.reset();
    setWindow(this.viewModel.getEmployeeProperty().getRole());
  }

  public void openWorkersView()
  {
    viewHandler.openView("workers");
  }
  public void openProjects()
  {
    viewHandler.openView("projects");
  }


  public void assignedWorkerTableClick()
  {
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
  private void setWindow(EmployeeRole employeeRole) {
    switch (employeeRole) {
      case WORKER -> {
        projectHBox.setVisible(true);
        projectHBox.setManaged(true);
        assignButton.setVisible(false);
      }
      case HR -> {
        projectHBox.setVisible(false);
        projectHBox.setManaged(false);
        assignButton.setVisible(false);
      }
      case PROJECT_MANAGER -> {
        projectHBox.setVisible(true);
        projectHBox.setManaged(true);
        assignButton.setVisible(false);
      }
      case MAIN_MANAGER -> {
        projectHBox.setVisible(true);
        projectHBox.setManaged(true);
        assignButton.setVisible(true);
      }
    }

  }

  @FXML public void assign(){
    viewHandler.openView("assignWorkersToProjectManager");
  }

  public void logOut()
  {
    viewHandler.openView("login");
  }
}
