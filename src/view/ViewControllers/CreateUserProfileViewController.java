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
import viewmodel.EmployeeView.CreateUserProfileViewModel;
import viewmodel.ViewModel;

public class CreateUserProfileViewController extends ViewControllerWithNavigationMenu
{

    @FXML
    private Button backButton;
    @FXML
    private TextField firstNameL;
    @FXML
    private Label firstNameErrorL;
    @FXML
    private TextField lastNameL;
    @FXML
    private Label lastNameErrorL;
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
    private CreateUserProfileViewModel viewModel;
    private ViewHandler viewHandler;
    @FXML private HBox projectHBox;

    @FXML  private ImageView bellImage;

    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (CreateUserProfileViewModel) viewModel;
        setChoiceBox();
        this.viewModel.load();
        super.init(this.viewModel, viewHandler, bellImage, avatarPic, nameL, workingNumberL, projectHBox);

        value = new SimpleBooleanProperty(true);

        bind();

        roleL.setValue("");
        colorLabels();
        setWindow(this.viewModel.getEmployee().getRole());

    }

    private void bind(){
        avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
        firstNameL.textProperty().bindBidirectional(this.viewModel.firstNameProperty());
        firstNameErrorL.textProperty().bindBidirectional(this.viewModel.firstNameEProperty());
        lastNameL.textProperty().bindBidirectional(this.viewModel.lastNameProperty());
        lastNameErrorL.textProperty().bindBidirectional(this.viewModel.lastNameEProperty());
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
        this.viewModel.lastNameValueProperty().addListener(e ->
        {
            if (this.viewModel.getLastNameValue()) {
                lastNameErrorL.setTextFill(Color.GREEN);
            } else {
                lastNameErrorL.setTextFill(Color.RED);
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
        genderFemale.setSelected(false);
        genderMale.setSelected(true);
        roleL.setValue("");
        setWindow(viewModel.getEmployee().getRole());
    }

    @FXML
    public void createProfile() {
        if (viewModel.createUserProfile()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Profile Created");
            alert.setHeaderText(viewModel.roleProperty().getValue() + " with working number: " + viewModel.getWorkingNumber() + " has been created successfully!");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                openHome();
            }
        }
    }

    public void setChoiceBox() {
        roleL.getItems().add("Main Manager");
        roleL.getItems().add("Project Manager");
        roleL.getItems().add("Worker");
        roleL.getItems().add("HR");
    }

    public void backButtonClick(){
        viewHandler.openLastWindow();
    }


}
