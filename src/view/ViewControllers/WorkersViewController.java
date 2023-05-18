package view.ViewControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
  @FXML private ImageView avatarPic;
  @FXML private TableView<viewmodel.WorkerView.WorkersTable> workerTable;
  @FXML private TableColumn<viewmodel.WorkerView.WorkersTable, String> name;
  @FXML private TableColumn<viewmodel.WorkerView.WorkersTable, String> role;
  @FXML private TableColumn<viewmodel.WorkerView.WorkersTable, String> workingNumber;
  @FXML private TableColumn<viewmodel.WorkerView.WorkersTable, String> email;
  @FXML private TableColumn<viewmodel.WorkerView.WorkersTable, Button> edit;
  @FXML
  private HBox projectHBox;
  @FXML private Button createNewProfileButton;
  @FXML private Label employeeName;
  @FXML private Label employeeWorkingNumber;
  @FXML private ImageView ringImage;
  private Region root;
  private WorkersViewModel viewModel;
  private ObservableList<viewmodel.WorkerView.WorkersTable> workersTables;


  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (WorkersViewModel) viewModel;
    this.viewModel.load();
    super.init(this.viewModel, viewHandler, ringImage, avatarPic, employeeName, employeeWorkingNumber, projectHBox);


    setWindow(this.viewModel.getEmployee().getRole());
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
    workerTable.setItems(((WorkersViewModel) viewModel).getWorkersTable());
    PropertyValueFactory<viewmodel.WorkerView.WorkersTable, Button> button = new PropertyValueFactory("button");
    edit.setCellValueFactory(button);
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

  private void fillInTable(){
    workersTables.clear();
    for (int i = 0; i < this.viewModel.getWorkersTable().size(); i++) {
      workersTables.add(new WorkersTable(this.viewModel.getEmployeess().get(i)));
      Button button1 = new Button("");
      button1.setId("button-edit");
      Long index = (long) i;
      button1.setOnAction(e -> {
        workersButtonTableClick(index);
        viewHandler.openView("editProfile");
      });
      workersTables.get(i).setButton(button1);
    }
  }

  public void workerTableClick()
  {
    if (workerTable.getSelectionModel().getSelectedItem() != null) {
      if(viewModel.isMainManager(workerTable.getSelectionModel().getSelectedItem().getNumber())){
        viewModel.chooseWorker(
            workerTable.getSelectionModel().getSelectedItem().getNumber());
        viewHandler.openView("projectManagerPage");
      }
      else if(viewModel.isWorker(workerTable.getSelectionModel().getSelectedItem().getNumber())){
        viewModel.chooseWorker(
            workerTable.getSelectionModel().getSelectedItem().getNumber());
        viewHandler.openView("workerProfile");
      }
      else{
        viewModel.chooseWorker(
            workerTable.getSelectionModel().getSelectedItem().getNumber());
        viewHandler.openView("hrAndMainManagerProfile");
      }
    }
  }
  public void workersButtonTableClick(Long index){
    workerTable.getSelectionModel().select(index.intValue());
    workerTableClick();
  }


  public void createNewProfile()
  {
    viewHandler.openView("createUserProfile");
  }

  public void setWindow(EmployeeRole employeeRole) {
    super.setWindow(employeeRole);
    switch (employeeRole) {
      case WORKER -> {
        edit.setVisible(false);
        createNewProfileButton.setVisible(false);
      }
      case HR -> {
        edit.setVisible(true);
        createNewProfileButton.setVisible(true);
      }
      case PROJECT_MANAGER -> {
        edit.setVisible(false);
        createNewProfileButton.setVisible(false);
      }
      case MAIN_MANAGER -> {
        edit.setVisible(false);
        createNewProfileButton.setVisible(false);
      }
    }

  }
}
