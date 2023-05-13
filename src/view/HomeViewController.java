package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.Employee;
import model.EmployeeRole;
import viewmodel.EditTaskViewModel;
import viewmodel.HomeViewModel;
import viewmodel.ViewModel;

public class HomeViewController implements ViewController
{
    public Label nameL;
    public Label workingNumberL;
    @FXML
    private HBox projectHBox;
    private Region root;
    @FXML public ImageView avatarPic;
    private HomeViewModel viewModel;
    private ViewHandler viewHandler;
    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
    {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (HomeViewModel) viewModel;
        this.viewModel.load();
        avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
        nameL.textProperty().bind(this.viewModel.nameProperty());
        workingNumberL.textProperty().bind(this.viewModel.workingNumberProperty());
        this.viewModel.employeePropertyProperty().addListener((observable, oldValue, newValue) -> {
            setWindow(((Employee) newValue).getRole());
        });
        this.viewModel.load();
        setWindow(this.viewModel.getEmployeeProperty().getRole());
    }
    @Override
    public Region getRoot()
    {
        return root;
    }

    @Override
    public void reset() {
        this.viewModel.reset();
        setWindow(this.viewModel.getEmployeeProperty().getRole());
    }

    public void openProjects()
    {
        viewHandler.openView("projects");
    }
    public void openWorkers()
    {
        viewHandler.openView("workers");
    }
    public void logOut()
    {
        viewHandler.openView("login");
    }
    private void setWindow(EmployeeRole employeeRole)
    {
        switch (employeeRole)
        {
            case WORKER ->
            {
                projectHBox.setVisible(true);
                projectHBox.setManaged(true);
            }
            case HR ->
            {
                projectHBox.setVisible(false);
                projectHBox.setManaged(false);
            }
            case PROJECT_MANAGER ->
            {
                projectHBox.setVisible(true);
                projectHBox.setManaged(true);
            }
            case MAIN_MANAGER ->
            {
                projectHBox.setVisible(true);
                projectHBox.setManaged(true);
            }
        }
    }
}
