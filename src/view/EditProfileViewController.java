package view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import model.EmployeeRole;
import viewmodel.CreateUserProfileViewModel;
import viewmodel.EditProfileViewModel;
import viewmodel.ViewModel;

import static model.EmployeeRole.*;

public class EditProfileViewController implements ViewController {

  @FXML
  private Button backButton;
  @FXML
  private TextField firstNameL;
  @FXML
  private Label firstNameErrorL;

  @FXML
  private ImageView avatarPic;
  @FXML
  private TextField emailL;
  @FXML
  private Label emailErrorL;
  @FXML
  private ChoiceBox<String> roleL;
  @FXML
  private Label roleErrorL;
  @FXML
  private TextField phoneNumberL;
  @FXML
  private Label phoneNumberErrorL;
  @FXML
  private PasswordField passwordL;
  @FXML
  private Label passwordErrorL;
  @FXML
  private DatePicker dobL;
  @FXML
  private Label dobErrorL;
  @FXML
  private RadioButton genderMale;
  @FXML
  private RadioButton genderFemale;

  @FXML
  private BooleanProperty value;
  @FXML
  private Label workingNumberL;
  @FXML
  private Label nameL;

  private Region root;
  private EditProfileViewModel viewModel;
  private ViewHandler viewHandler;
  @FXML private HBox projectHBox;

  @Override
  public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
    this.root = root;
    System.out.println(root);
    this.viewHandler = viewHandler;
    this.viewModel = (EditProfileViewModel) viewModel;
    setChoiceBox();
    this.viewModel.load();
    this.dobErrorL.setText(null);
    this.emailErrorL.setText(null);
    this.firstNameErrorL.setText(null);
    this.roleErrorL.setText(null);
    this.phoneNumberErrorL.setText(null);
    this.passwordErrorL.setText(null);

    avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
    firstNameL.textProperty().bindBidirectional(this.viewModel.firstNameProperty());
    firstNameErrorL.textProperty().bindBidirectional(this.viewModel.firstNameEProperty());
    emailL.textProperty().bindBidirectional(this.viewModel.emailProperty());
    emailErrorL.textProperty().bindBidirectional(this.viewModel.emailEProperty());
    roleErrorL.textProperty().bindBidirectional(this.viewModel.roleEProperty());
    phoneNumberL.textProperty().bindBidirectional(this.viewModel.phoneNumberProperty());
    phoneNumberErrorL.textProperty().bindBidirectional(this.viewModel.phoneNumberEProperty());
    passwordL.textProperty().bindBidirectional(this.viewModel.passwordProperty());
    passwordErrorL.textProperty().bindBidirectional(this.viewModel.passwordEProperty());
    dobL.valueProperty().bindBidirectional(this.viewModel.dobProperty());
    dobErrorL.textProperty().bindBidirectional(this.viewModel.dobEProperty());
    roleL.valueProperty().bindBidirectional(this.viewModel.roleProperty());
    colorLabels();
    value = new SimpleBooleanProperty(true);
    this.value.bindBidirectional(this.viewModel.genderProperty());
    this.nameL.textProperty().bind(this.viewModel.nameProperty());
    this.workingNumberL.textProperty().bind(this.viewModel.workingNumberFProperty());
    this.viewModel.load();
    setWindow(this.viewModel.getEmployee().getRole());
    genreSetting();
  }

  public void genreSetting() {
    if(viewModel.userIsWoman()){
      genderFemale.setSelected(true);
      System.out.println(viewModel.userIsWoman());
    }
    else{
      genderMale.setSelected(true);
    }
  }
  public void radioButtons() {
    value.setValue(genderMale.isSelected());
  }


  private void colorLabels() {
    this.viewModel.firstNameValueProperty().addListener(e ->
    {
      if (this.viewModel.getFirstNameValue()) {
        firstNameErrorL.setTextFill(Color.GREEN);
      } else {
        firstNameErrorL.setTextFill(Color.RED);
      }
    });
    this.viewModel.emailValueProperty().addListener(e ->
    {
      if (this.viewModel.getEmailValue()) {
        emailErrorL.setTextFill(Color.GREEN);
      } else {
        emailErrorL.setTextFill(Color.RED);
      }
    });
    this.viewModel.roleValueProperty().addListener(e ->
    {
      if (this.viewModel.getRoleValue()) {
        roleErrorL.setTextFill(Color.GREEN);
      } else {
        roleErrorL.setTextFill(Color.RED);
      }
    });
    this.viewModel.phoneNumberValueProperty().addListener(e ->
    {
      if (this.viewModel.getPhoneNumberValue()) {
        phoneNumberErrorL.setTextFill(Color.GREEN);
      } else {
        phoneNumberErrorL.setTextFill(Color.RED);
      }
    });
    this.viewModel.passwordValueProperty().addListener(e ->
    {
      if (this.viewModel.getPasswordValue()) {
        passwordErrorL.setTextFill(Color.GREEN);
      } else {
        passwordErrorL.setTextFill(Color.RED);
      }
    });
    this.viewModel.dobValueProperty().addListener(e ->
    {
      if (this.viewModel.getDobValue()) {
        dobErrorL.setTextFill(Color.GREEN);
      } else {
        dobErrorL.setTextFill(Color.RED);
      }
    });
  }

  @Override
  public Region getRoot() {
    System.out.println(root);
    return root;
  }

  @Override
  public void reset() {
    viewModel.reset();
    genreSetting();
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

  @FXML
  public void saveChanges() {
    if (viewModel.createUserProfile()) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("User Profile Edited");
      alert.setHeaderText(viewModel.roleProperty().getValue() + " with working number: " + viewModel.getWorkingNumber() + " has been edited successfully!");
      alert.showAndWait();
      if (alert.getResult() == ButtonType.OK) {
        openWorkersView();
      }
    }
  }

  public void openProjects() {
    viewHandler.openView("projects");
  }

  public void setChoiceBox() {
    roleL.getItems().add("Main Manager");
    roleL.getItems().add("Project Manager");
    roleL.getItems().add("Worker");
    roleL.getItems().add("HR");
    roleL.setValue("HR");
  }

  public void backButtonClick(){
    viewHandler.openView("workers");
  }
  public void openWorkersView() {
    viewHandler.openView("workers");
  }

  public void openHome() {
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

  public void changePassword()
  {
    if (viewModel.editPassword()) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("User password have been changed");
      alert.setHeaderText("Password of " + viewModel.roleProperty().getValue() + " with working number: " + viewModel.getWorkingNumber() + " has been edited successfully!");
      alert.showAndWait();
      if (alert.getResult() == ButtonType.OK) {
        openWorkersView();
      }
    }
  }
}
