package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.Employee;
import model.EmployeeRole;
import viewmodel.TaskView.TasksTable;
import viewmodel.TaskView.WorkersTable;
import viewmodel.TasksViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkerView.WorkersViewModel;

public class WorkersViewController implements ViewController
{
  @FXML public ImageView avatarPic;
  @FXML public TableView<viewmodel.WorkerView.WorkersTable> workerTable;
  @FXML public TableColumn<viewmodel.WorkerView.WorkersTable, String> name;
  @FXML public TableColumn<viewmodel.WorkerView.WorkersTable, String> role;
  @FXML public TableColumn<viewmodel.WorkerView.WorkersTable, String> workingNumber;
  @FXML public TableColumn<viewmodel.WorkerView.WorkersTable, String> email;
  @FXML
  private HBox projectHBox;
  @FXML public Button createNewProfileButton;
  @FXML public Label employeeName;
  @FXML public Label employeeWorkingNumber;

  private Region root;
  private WorkersViewModel viewModel;
  private ViewHandler viewHandler;
  private ObservableList<WorkersTable> workersTables;


  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (WorkersViewModel) viewModel;
    this.viewModel.load();
    avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
    name.setCellValueFactory(
        cellData -> cellData.getValue().getNameProperty());
    workingNumber.setCellValueFactory(
        cellData -> cellData.getValue().getNumberProperty());
    email.setCellValueFactory(
        cellData -> cellData.getValue().getEmailProperty());
    role.setCellValueFactory(
        cellData -> cellData.getValue().getRoleProperty());
    workerTable.setItems(((WorkersViewModel) viewModel).getWorkersTable());
    createNewProfileButton.setVisible(false);
    if(((WorkersViewModel) viewModel).displayAddButton()){
      createNewProfileButton.setVisible(true);
    }
    employeeName.textProperty().bindBidirectional(this.viewModel.getEmployeeName());
    employeeWorkingNumber.textProperty().bindBidirectional(this.viewModel.getEmployeeWorkingNumber());
    this.viewModel.employeePropertyProperty().addListener((observable, oldValue, newValue) -> {
      setWindow(((Employee) newValue).getRole());
    });
    setWindow(this.viewModel.getEmployeeProperty().getRole());
  }

  @Override public Region getRoot()
  {
    return root;
  }

  @Override
  public void reset() {
    viewModel.reset();
    setWindow(this.viewModel.getEmployeeProperty().getRole());
  }

  public void workerTableClick()
  {
    if (workerTable.getSelectionModel().getSelectedItem() != null) {
      if(viewModel.isMainManager(workerTable.getSelectionModel().getSelectedItem().getNumber())){
        viewModel.chooseWorker(
            workerTable.getSelectionModel().getSelectedItem().getNumber());
        viewHandler.openView("projectManagerPage");
      }
    }
  }
  public void projectButtonTableClick() {

  }
  public void openProjects()
  {
    viewHandler.openView("projects");
  }

  public void createNewProfile()
  {
    viewHandler.openView("createUserProfile");
  }


  public void openWorkersView()
  {
    viewHandler.openView("workers");
  }
  public void openHome()
  {
    viewHandler.openView("home");
  }
  private void setWindow(EmployeeRole employeeRole) {
    switch (employeeRole) {
      case WORKER -> {
        projectHBox.setVisible(true);
        projectHBox.setManaged(true);
      }
      case HR -> {
        projectHBox.setVisible(false);
        projectHBox.setManaged(false);
      }
      case PROJECT_MANAGER -> {

        projectHBox.setVisible(true);
        projectHBox.setManaged(true);
      }
      case MAIN_MANAGER -> {
        projectHBox.setVisible(true);
        projectHBox.setManaged(true);
      }
    }

  }
}
