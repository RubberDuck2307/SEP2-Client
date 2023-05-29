package view.ViewControllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import view.ViewController;
import view.ViewHandler;
import viewmodel.EmployeeView.EditProfileViewModel;
import viewmodel.ViewModel;

public class EditProfileViewController extends ViewControllerWithNavigationMenu
{

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
  @FXML private HBox projectHBox;

  @FXML private ImageView bellImage;

  @Override
  public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
    this.root = root;
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
    value = new SimpleBooleanProperty(true);

    this.viewModel.load();

    super.init(this.viewModel, viewHandler, bellImage, avatarPic, nameL, workingNumberL, projectHBox);

    bind();

    colorLabels();


    setWindow(this.viewModel.getEmployee().getRole());
    genreSetting();
  }

  private void bind(){
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
    this.value.bindBidirectional(this.viewModel.genderProperty());
  }
  public void genreSetting() {
    if(viewModel.userIsWoman()){
      genderFemale.setSelected(true);
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
    return root;
  }

  @Override
  public void reset() {
    viewModel.reset();
    genreSetting();
  }

  protected void setWindow(EmployeeRole employeeRole) {
    super.setWindow(employeeRole);
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
