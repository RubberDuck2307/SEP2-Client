package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.LoginViewModel;
import viewmodel.ViewModel;

import java.awt.*;

public class LoginViewController implements ViewController {

    private ViewHandler viewHandler;
    private LoginViewModel viewModel;

    @FXML
    public TextField workingNumberField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    private Region root;


    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = (LoginViewModel) viewModel;
        this.root = root;
        bind();

    }

    private void bind() {
        passwordField.textProperty().bindBidirectional(viewModel.passwordPropertyProperty());
        workingNumberField.textProperty().bindBidirectional(viewModel.workingNumberPropertyProperty());
        errorLabel.textProperty().bindBidirectional(viewModel.errorPropertyProperty());
    }

    @Override
    public Region getRoot() {
        return root;
    }

    @Override
    public void reset() {
        viewModel.reset();
    }

    @FXML
    private void login() {
        if (viewModel.login()){
            viewHandler.openView("home");
        }
    }

}
