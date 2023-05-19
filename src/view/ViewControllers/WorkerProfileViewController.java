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
import viewmodel.WorkerView.WorkerProfileViewModel;

public class WorkerProfileViewController extends ViewControllerWithNavigationMenu
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
  @FXML private ImageView bellImage;
  private Region root;
  private WorkerProfileViewModel viewModel;


  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (WorkerProfileViewModel) viewModel;
    this.viewModel.load();
    super.init(this.viewModel, viewHandler , bellImage, avatarPic, employeeName, employeeWorkingNumber, projectHBox);
    bind();

    setProjectTable();

    setTaskTable();

    setWindow(this.viewModel.getEmployee().getRole());
    setWorkerPicture();
  }

  private void bind()
  {
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
  }

  private void setProjectTable(){
    projectDeadline.setCellValueFactory(
            cellData -> cellData.getValue().deadlineProperty());
    projectTitle.setCellValueFactory(
            cellData -> cellData.getValue().titleProperty());
    currentProjectsTable.setItems(
            ((WorkerProfileViewModel) viewModel).getCurrentProjectsTableTable());
  }
  private void setTaskTable(){
    taskTitle.setCellValueFactory(
            cellData -> cellData.getValue().getTitleProperty());
    taskStatus.setCellValueFactory(
            cellData -> cellData.getValue().getStatusProperty());
    taskProjectName.setCellValueFactory(
            cellData -> cellData.getValue().projectNameProperty());
    taskTable.setItems(
            ((WorkerProfileViewModel) viewModel).getTaskTable());

  }

  public void setWorkerPicture(){
    if (this.viewModel.isWorkerWoman())
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

  @Override public void reset()
  {
    viewModel.reset();
    setWorkerPicture();
  }

  public void goBackButton()
  {
    viewHandler.openLastWindow();
  }



  protected void setWindow(EmployeeRole employeeRole)
  {
    super.setWindow(employeeRole);
  }
}
