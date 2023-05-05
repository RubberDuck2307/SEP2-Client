package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.EmployeeRole;
import viewmodel.CreateUserProfileViewModel;
import viewmodel.ViewModel;

public class CreateUserProfileViewController implements ViewController
{
    
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
    public TextField emailL;
    @FXML
    public Label emailErrorL;
    @FXML
    public ChoiceBox<EmployeeRole> roleL;
    @FXML
    public Label roleErrorL;
    @FXML
    public TextField phoneNumberL;
    @FXML
    public Label phoneNumberErrorL;
    @FXML
    public TextField passwordL;
    @FXML
    public Label passwordErrorL;
    @FXML
    public DatePicker dobL;
    @FXML
    public Label dobErrorL;
    @FXML
    public TextField jobTitleL;
    
    private Region root;
    private CreateUserProfileViewModel viewModel;
    private ViewHandler viewHandler;
    
    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
    {
        this.root = root;
        System.out.println(root);
        this.viewHandler = viewHandler;
//        this.viewModel = (CreateUserProfileViewModel) viewModel;
//        setChoiceBox();
//        this.dobErrorL.setText(null);
//        this.emailErrorL.setText(null);
//        this.lastNameErrorL.setText(null);
//        this.firstNameErrorL.setText(null);
//        this.roleErrorL.setText(null);
//        this.phoneNumberErrorL.setText(null);
//        this.passwordErrorL.setText(null);
//        firstNameL.textProperty().bindBidirectional(this.viewModel.firstNameProperty());
//        firstNameErrorL.textProperty().bindBidirectional(this.viewModel.firstNameEProperty());
//        lastNameL.textProperty().bindBidirectional(this.viewModel.lastNameProperty());
//        lastNameErrorL.textProperty().bindBidirectional(this.viewModel.lastNameEProperty());
//        emailL.textProperty().bindBidirectional(this.viewModel.emailProperty());
//        emailErrorL.textProperty().bindBidirectional(this.viewModel.emailEProperty());
//        roleErrorL.textProperty().bindBidirectional(this.viewModel.roleEProperty());
//        phoneNumberL.textProperty().bindBidirectional(this.viewModel.phoneNumberProperty());
//        phoneNumberErrorL.textProperty().bindBidirectional(this.viewModel.phoneNumberEProperty());
//        passwordL.textProperty().bindBidirectional(this.viewModel.passwordProperty());
//        passwordErrorL.textProperty().bindBidirectional(this.viewModel.passwordEProperty());
//        roleL.setItems(this.viewModel.roleProperty());
    }
    
    @Override
    public Region getRoot()
    {
        System.out.println(root);
        return root;
    }


    @FXML
    public void createProfile()
    {
        if (viewModel.createUserProfile()) openProjects();
    }
    
    
    public void openProjects()
    {
        viewHandler.openView("projects");
    }
    
    public void setChoiceBox()
    {
        roleL.getItems().add(EmployeeRole.MAIN_MANAGER);
        roleL.getItems().add(EmployeeRole.PROJECT_MANAGER);
        roleL.getItems().add(EmployeeRole.WORKER);
        roleL.setValue(EmployeeRole.HR);
    }
}
