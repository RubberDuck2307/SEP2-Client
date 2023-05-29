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
import viewmodel.WorkerView.HrAndMainManagerProfileViewModel;
import viewmodel.WorkerView.MainManagerHomeViewModel;
import viewmodel.WorkerView.ProjectManagerProfileViewModel;
import viewmodel.WorkerView.WorkersViewModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainManagerHomeViewController extends ViewControllerWithNavigationMenu
{
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
  private MainManagerHomeViewModel viewModel;
  private ViewHandler viewHandler;
  @FXML private ImageView bellImage;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (MainManagerHomeViewModel) viewModel;
    this.viewModel.load();

    bind();

    super.init(this.viewModel, viewHandler,bellImage, avatarPic, employeeName, employeeWorkingNumber, projectHBox);
    setWindow(this.viewModel.getEmployee().getRole());
  }

  private void bind(){
    managerName.textProperty().bindBidirectional(this.viewModel.managerNameProperty());
    managerEmail.textProperty().bindBidirectional(this.viewModel.managerEmailProperty());
    managerDateOfBirth.textProperty().bindBidirectional(this.viewModel.managerDateOfBirthProperty());
    managerRole.textProperty().bindBidirectional(this.viewModel.managerRoleProperty());
    managerPhoneNumber.textProperty().bindBidirectional(this.viewModel.managerPhoneNumberProperty());
    workerName2.textProperty().bindBidirectional(this.viewModel.getWorkerName2());
  }
  @Override public Region getRoot()
  {
    return root;
  }

  @Override public void reset()
  {

    viewModel.load();
    setWindow(viewModel.getEmployee().getRole());
  }


  public void goBackButton()
  {
    viewHandler.openLastWindow();
  }



  public void logOut()
  {
    viewModel.logOut();
    viewHandler.openView("login");
  }

  public void openNotes(){viewHandler.openView("notes");}
}
