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

public class HrHomeViewController extends ViewControllerWithNavigationMenu
{
  @FXML public TableView<NotificationTable> notificationTable;
  @FXML public TableColumn<NotificationTable, String> messageNotificationColumn;
  @FXML public TableColumn<NotificationTable, Button> deleteNotificationColumn;
  @FXML private Label workerName2;
  @FXML
  private HBox projectHBox;
  @FXML private Label managerName;
  @FXML private Label managerRole;
  @FXML private Label managerDateOfBirth;
  @FXML private Label managerPhoneNumber;
  @FXML private Label managerEmail;

  @FXML private ImageView bellImage;
  private Region root;
  @FXML private Label employeeName;
    @FXML private Label employeeWorkingNumber;
  private HrHomeViewModel viewModel;
  private ViewHandler viewHandler;

  @FXML private ImageView avatarPic;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (HrHomeViewModel) viewModel;
    this.viewModel.load();
    super.init(this.viewModel, viewHandler, bellImage, avatarPic,  employeeName, employeeWorkingNumber, projectHBox);
    managerName.textProperty().bindBidirectional(this.viewModel.managerNameProperty());
    managerEmail.textProperty().bindBidirectional(this.viewModel.managerEmailProperty());
    managerDateOfBirth.textProperty().bindBidirectional(this.viewModel.managerDateOfBirthProperty());
    managerRole.textProperty().bindBidirectional(this.viewModel.managerRoleProperty());
    managerPhoneNumber.textProperty().bindBidirectional(this.viewModel.managerPhoneNumberProperty());

    messageNotificationColumn.setCellValueFactory(
        cellData -> cellData.getValue().textProperty());

    notificationTable.setItems(((HrHomeViewModel) viewModel).getNotificationList());

    workerName2.textProperty()
        .bindBidirectional(this.viewModel.workerName2Property());

    setWindow(this.viewModel.getEmployee().getRole());

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


  public void goBackButton()
  {
    viewHandler.openLastWindow();
  }



  @FXML public void assign(){
    viewHandler.openView("assignWorkersToProjectManager");
  }

  public void logOut()
  {
    viewHandler.openView("login");
  }

  public void openNotes(){viewHandler.openView("notes");}
}
