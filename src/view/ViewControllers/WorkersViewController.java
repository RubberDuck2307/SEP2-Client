package view.ViewControllers;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.Employee;
import model.EmployeeRole;
import view.ViewHandler;
import viewmodel.ViewModel;
import viewmodel.WorkerView.WorkersTable;
import viewmodel.WorkerView.WorkersViewModel;

public class WorkersViewController extends ViewControllerWithNavigationMenu
{
  @FXML public ImageView avatarPic;
  @FXML public TableView<viewmodel.WorkerView.WorkersTable> workerTable;
  @FXML public TableColumn<viewmodel.WorkerView.WorkersTable, String> name;
  @FXML public TableColumn<viewmodel.WorkerView.WorkersTable, String> role;
  @FXML public TableColumn<viewmodel.WorkerView.WorkersTable, String> workingNumber;
  @FXML public TableColumn<viewmodel.WorkerView.WorkersTable, String> email;
  @FXML public TableColumn<viewmodel.WorkerView.WorkersTable, Button> edit;
  public TableColumn<viewmodel.WorkerView.WorkersTable, Button> delete;
  @FXML
  private HBox projectHBox;
  @FXML public Button createNewProfileButton;
  @FXML public Label employeeName;
  @FXML public Label employeeWorkingNumber;
  @FXML private ImageView ringImage;

  private Region root;
  private WorkersViewModel viewModel;
  private ViewHandler viewHandler;
  private ObservableList<viewmodel.WorkerView.WorkersTable> workersTables;


  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (WorkersViewModel) viewModel;
    this.viewModel.load();
    super.init(this.viewModel, viewHandler, ringImage, avatarPic, employeeName,
        employeeWorkingNumber, projectHBox);

    setWindow(this.viewModel.getEmployee().getRole());

    workersTables = FXCollections.observableArrayList();

    setWorkersTable();
    fillInTable();
  }


  private void setWorkersTable(){
    name.setCellValueFactory(
            cellData -> cellData.getValue().getNameProperty());
    workingNumber.setCellValueFactory(
            cellData -> cellData.getValue().getNumberProperty());
    email.setCellValueFactory(
            cellData -> cellData.getValue().getEmailProperty());
    role.setCellValueFactory(
            cellData -> cellData.getValue().getRoleProperty());
    workerTable.setItems( viewModel.getWorkersTable());
    PropertyValueFactory<viewmodel.WorkerView.WorkersTable, Button> button = new PropertyValueFactory("button");
    PropertyValueFactory<viewmodel.WorkerView.WorkersTable, Button> deleteButton = new PropertyValueFactory("deleteButton");
    edit.setCellValueFactory(button);
    delete.setCellValueFactory(deleteButton);
    workersTables = FXCollections.observableArrayList();

    workerTable.setItems(workersTables);

  }

  @Override public Region getRoot()
  {
    return root;
  }

  @Override
  public void reset() {
    viewModel.reset();
    setWindow(this.viewModel.getEmployee().getRole());
    fillInTable();
  }

  private void fillInTable()
  {
    workersTables.clear();
    for (int i = 0; i < this.viewModel.getWorkersTable().size(); i++)
    {
      Employee employee = this.viewModel.getEmployeess().get(i);
      workersTables.add(new WorkersTable(employee));
      Button button1 = new Button("");
      Button button2 = new Button("");
      button1.setId("button-edit");
      button2.setId("delete-button");
      button1.setOnAction(e ->
      {
        workersButtonTableClick(employee);
        viewHandler.openView("editProfile");
      });
      button2.setOnAction(e ->
      {
        alertWindow(employee);
      });
      workersTables.get(i).setButton(button1);
      workersTables.get(i).setDeleteButton(button2);
    }
  }
  private void alertWindow(Employee employee)
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Deleting User Profile");
    alert.setHeaderText("Are you sure you want to delete " + employee.getName() + " ?");
    alert.showAndWait();
    if (alert.getResult() == ButtonType.OK)
    {
      delete(employee);
    }
  }

  public void delete(Employee employee)
  {
    viewModel.delete(employee);
    reset();
  }


  public void workerTableClick()
  {
    if (workerTable.getSelectionModel().getSelectedItem() != null)
    {
      viewModel.chooseWorker(workerTable.getSelectionModel().getSelectedItem().getNumber());
      if (viewModel.isMainManager(workerTable.getSelectionModel().getSelectedItem().getNumber()))
      {
        viewHandler.openView("projectManagerPage");
      }
      else if (viewModel.isWorker(workerTable.getSelectionModel().getSelectedItem().getNumber()))
      {
        viewHandler.openView("workerProfile");
      }
      else
      {
        viewHandler.openView("hrAndMainManagerProfile");
      }
    }
  }


  public void workersButtonTableClick(Employee employee)
  {
    viewModel.chooseWorker(employee);
  }

  public void createNewProfile()
  {
    viewHandler.openView("createUserProfile");
  }


  protected void setWindow(EmployeeRole employeeRole) {
    super.setWindow(employeeRole);
    switch (employeeRole) {
      case WORKER -> {
        edit.setVisible(false);
        createNewProfileButton.setVisible(false);
        delete.setVisible(false);
      }
      case HR -> {
        edit.setVisible(true);
        createNewProfileButton.setVisible(true);
        delete.setVisible(true);
      }
      case PROJECT_MANAGER -> {
        edit.setVisible(false);
        createNewProfileButton.setVisible(false);
        delete.setVisible(false);
      }
      case MAIN_MANAGER -> {
        edit.setVisible(false);
        createNewProfileButton.setVisible(false);
        delete.setVisible(false);
      }
    }

  }
}
