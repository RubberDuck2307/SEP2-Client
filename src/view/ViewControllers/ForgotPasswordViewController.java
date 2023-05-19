package view.ViewControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewHandler;
import viewmodel.EmployeeView.ForgotPasswordViewModel;
import viewmodel.ViewModel;

import java.awt.*;

public class ForgotPasswordViewController implements ViewController {

    @FXML private TextField workingNumberField;
    private ViewHandler viewHandler;
    private ForgotPasswordViewModel viewModel;
    private Region root;
    @FXML private Label errorLabel;
    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
        this.viewModel = (ForgotPasswordViewModel) viewModel;
        this.viewHandler = viewHandler;
        this.root = root;
        bind();

    }

    public void bind(){
        workingNumberField.textProperty().bindBidirectional(viewModel.workingNumberProperty());
        errorLabel.textProperty().bindBidirectional(viewModel.errorProperty());
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
    private void buttonClick(){
        if (viewModel.sendNotification()){
            viewHandler.openView("login");
        }
}
}
