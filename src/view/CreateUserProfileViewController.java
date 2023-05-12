package view;

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
import viewmodel.CreateUserProfileViewModel;
import viewmodel.ViewModel;

import static model.EmployeeRole.*;

public class CreateUserProfileViewController implements ViewController {

    @FXML
    public Button backButton;
    @FXML
    public TextField firstNameL;
    @FXML
    public Label firstNameErrorL;
    @FXML
    public TextField lastNameL;
    @FXML
    public Label lastNameErrorL;
    @FXML
    public ImageView avatarPic;
    @FXML
    public TextField emailL;
    @FXML
    public Label emailErrorL;
    @FXML
    public ChoiceBox<String> roleL;
    @FXML
    public Label roleErrorL;
    @FXML
    public TextField phoneNumberL;
    @FXML
    public Label phoneNumberErrorL;
    @FXML
    public PasswordField passwordL;
    @FXML
    public Label passwordErrorL;
    @FXML
    public DatePicker dobL;
    @FXML
    public Label dobErrorL;
    @FXML
    public TextField jobTitleL;
    @FXML
    public RadioButton genderMale;
    @FXML
    public RadioButton genderFemale;
    @FXML
    public ToggleGroup gender;
    @FXML
    public RadioButton selectedButton;
    @FXML
    public BooleanProperty value;
    @FXML
    public Label workingNumberL;
    @FXML
    public Label nameL;

    private Region root;
    private CreateUserProfileViewModel viewModel;
    private ViewHandler viewHandler;
    @FXML private HBox projectHBox;

    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
        this.root = root;
        System.out.println(root);
        this.viewHandler = viewHandler;
        this.viewModel = (CreateUserProfileViewModel) viewModel;
        setChoiceBox();
        this.viewModel.load();
        this.dobErrorL.setText(null);
        this.emailErrorL.setText(null);
        this.lastNameErrorL.setText(null);
        this.firstNameErrorL.setText(null);
        this.roleErrorL.setText(null);
        this.phoneNumberErrorL.setText(null);
        this.passwordErrorL.setText(null);
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
        roleL.setValue("");
        colorLabels();
        value = new SimpleBooleanProperty(true);
        this.value.bindBidirectional(this.viewModel.genderProperty());
        this.nameL.textProperty().bind(this.viewModel.nameProperty());
        this.workingNumberL.textProperty().bind(this.viewModel.workingNumberFProperty());
        this.viewModel.load();
        setWindow(this.viewModel.getEmployee().getRole());

    }

    public void radioButtons() {
        System.out.println(genderMale.isSelected());
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
        System.out.println(root);
        return root;
    }

    @Override
    public void reset() {

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

    public void openProjects() {
        viewHandler.openView("projects");
    }

    public void setChoiceBox() {
        roleL.getItems().add("Main Manager");
        roleL.getItems().add("Project Manager");
        roleL.getItems().add("Worker");
        roleL.setValue("HR");
    }

    public void openWorkersView() {
        viewHandler.openView("workers");
    }

    public void openHome() {
        viewHandler.openView("home");
    }
}
