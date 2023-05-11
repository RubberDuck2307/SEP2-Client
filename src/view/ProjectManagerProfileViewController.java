package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.ProjectView.ProjectsViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkerView.ProjectManagerProfileViewModel;
import viewmodel.WorkerView.WorkersViewModel;

public class ProjectManagerProfileViewController implements ViewController
{
  @FXML public Button backButton;
  @FXML public ImageView avatarPic;
  @FXML public Label managerName;
  @FXML public Label managerRole;
  @FXML public Label managerDateOfBirth;
  @FXML public Label managerPhoneNumber;
  @FXML public Label managerEmail;
  /*current project*/
  @FXML public TableView<ProjectsTable> currentProjectsTable;
  @FXML public TableColumn<ProjectsTable, String> projectTitle;
  @FXML public TableColumn<ProjectsTable, String> projectDeadline;



  /*assigned workers*/
  @FXML public TableView<viewmodel.WorkerView.WorkersTable> assignWorkersTable;
  @FXML public TableColumn<viewmodel.WorkerView.WorkersTable, String> workerNumber;
  @FXML public TableColumn<viewmodel.WorkerView.WorkersTable, String> workerName;
  @FXML public TableColumn <viewmodel.WorkerView.WorkersTable, String> workerEmail;
  @FXML public Label employeeName;
  @FXML public Label employeeWorkingNumber;

  private Region root;
  private ProjectManagerProfileViewModel viewModel;
  private ViewHandler viewHandler;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (ProjectManagerProfileViewModel) viewModel;
    this.viewModel.load();
    employeeName.textProperty().bindBidirectional(this.viewModel.getEmployeeName());
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
    assignWorkersTable.setItems(((ProjectManagerProfileViewModel) viewModel).getWorkersTable());

    projectDeadline.setCellValueFactory(
        cellData -> cellData.getValue().deadlineProperty());
    projectTitle.setCellValueFactory(
        cellData -> cellData.getValue().titleProperty());
    currentProjectsTable.setItems(((ProjectManagerProfileViewModel) viewModel).getCurrentProjectsTableTable());
  }

  @Override public Region getRoot()
  {
    return root;
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

}
