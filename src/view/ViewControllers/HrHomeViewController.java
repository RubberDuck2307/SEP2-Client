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

public class HrHomeViewController implements ViewController
{
  @FXML public TableView<NotificationTable> notificationTable;
  @FXML public TableColumn<NotificationTable, String> messageNotificationColumn;
  @FXML public TableColumn<NotificationTable, Button> deleteNotificationColumn;
  @FXML private ObservableList<NotificationTable> notificationTables;
  @FXML private Label workerName2;

  @FXML private ImageView avatarPic;
  @FXML
  private HBox projectHBox;
  @FXML private Label managerName;
  @FXML private Label managerRole;
  @FXML private Label managerDateOfBirth;
  @FXML private Label managerPhoneNumber;
  @FXML private Label managerEmail;


  @FXML private Label employeeName;
  @FXML private Label employeeWorkingNumber;


  private Region root;
  private HrHomeViewModel viewModel;
  private ViewHandler viewHandler;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (HrHomeViewModel) viewModel;
    this.viewModel.load();
    employeeName.textProperty().bindBidirectional(this.viewModel.getEmployeeName());

    avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
    employeeWorkingNumber.textProperty().bindBidirectional(this.viewModel.getEmployeeWorkingNumber());
    managerName.textProperty().bindBidirectional(this.viewModel.managerNameProperty());
    managerEmail.textProperty().bindBidirectional(this.viewModel.managerEmailProperty());
    managerDateOfBirth.textProperty().bindBidirectional(this.viewModel.managerDateOfBirthProperty());
    managerRole.textProperty().bindBidirectional(this.viewModel.managerRoleProperty());
    managerPhoneNumber.textProperty().bindBidirectional(this.viewModel.managerPhoneNumberProperty());

    messageNotificationColumn.setCellValueFactory(
        cellData -> cellData.getValue().messageProperty());
    notificationTable.setItems(((HrHomeViewModel) viewModel).getNotificationTable());

    workerName2.textProperty()
        .bindBidirectional(this.viewModel.workerName2Property());
    this.viewModel.employeePropertyProperty().addListener((observable, oldValue, newValue) -> {
      setWindow(((Employee) newValue).getRole());
    });
    setWindow(this.viewModel.getEmployeeProperty().getRole());


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

  @Override
  public void reset() {
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

  @FXML public void assign(){
    viewHandler.openView("assignWorkersToProjectManager");
  }

  public void logOut()
  {
    viewHandler.openView("login");
  }
}
