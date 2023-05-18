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
import viewmodel.WorkerView.ProjectManagerProfileViewModel;
import viewmodel.WorkerView.WorkersViewModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HrAndMainManagerProfileViewController extends ViewControllerWithNavigationMenu
{
  @FXML private Button backButton;
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
  @FXML private ImageView avatarPicture;
  @FXML private ImageView bellImage;

  private Region root;
  private HrAndMainManagerProfileViewModel viewModel;
  private ViewHandler viewHandler;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (HrAndMainManagerProfileViewModel) viewModel;
    this.viewModel.load();
    super.init(this.viewModel, viewHandler, bellImage, avatarPic, employeeName,
        employeeWorkingNumber, projectHBox);
    bind();
    isWoman();
    setWindow(this.viewModel.getEmployee().getRole());
  }

  @Override public Region getRoot()
  {
    return root;
  }

  @Override public void reset()
  {
    viewModel.reset();
    isWoman();
    setWindow(this.viewModel.getEmployee().getRole());
  }

  private void bind(){
    managerName.textProperty().bindBidirectional(this.viewModel.managerNameProperty());
    managerEmail.textProperty().bindBidirectional(this.viewModel.managerEmailProperty());
    managerDateOfBirth.textProperty().bindBidirectional(this.viewModel.managerDateOfBirthProperty());
    managerRole.textProperty().bindBidirectional(this.viewModel.managerRoleProperty());
    managerPhoneNumber.textProperty().bindBidirectional(this.viewModel.managerPhoneNumberProperty());
  }
  public void goBackButton()
  {
    viewHandler.openLastWindow();
  }

  public void isWoman(){
    if (this.viewModel.isProjectManagerWoman())
    {
      avatarPicture.setImage(new Image("/icons/woman-avatar.png"));
      //avatarPic.setImage(new Image("/icons/woman-avatar.png"));
    }
    else{
      avatarPicture.setImage(new Image("/icons/man-avatar.png"));
    }
  }
  protected void setWindow(EmployeeRole employeeRole) {
    super.setWindow(employeeRole);

  }

}
