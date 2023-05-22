package view.ViewControllers;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Employee;
import model.EmployeeRole;
import view.ViewController;
import view.ViewHandler;
import viewmodel.ViewModelWithNavigationMenu;

/**
 * Super class for all view controllers that have a navigation menu.
 * @author Anna Andrlova, Alex Bolfa, Cosmin Demian, Jan Metela, Arturs Ricards Rijnieks
 * @version 1.0 - May 2023
 */
public abstract class ViewControllerWithNavigationMenu implements ViewController {
    private ImageView bellImage;
    private ImageView avatarPic;
    private ViewModelWithNavigationMenu viewModel;
    protected ViewHandler viewHandler;
    @FXML
    private Label employeeName;
    @FXML
    private Label employeeWorkingNumber;
    private HBox projectHBox;

    public void init(ViewModelWithNavigationMenu viewModel, ViewHandler viewHandler, ImageView bellImage, ImageView avatarPic, Label employeeName, Label employeeWorkingNumber, HBox projectHBox) {
        this.viewModel = viewModel;
        this.bellImage = bellImage;
        this.bellImage.setVisible(false);
        this.avatarPic = avatarPic;
        this.employeeName = employeeName;
        this.employeeWorkingNumber = employeeWorkingNumber;
        this.viewHandler = viewHandler;
        this.projectHBox = projectHBox;
        bind();
        addListeners();
        viewModel.load();
    }

    private void addListeners() {
        this.viewModel.employeeProperty().addListener((observable, oldValue, newValue) -> {
            setWindow(newValue.getRole());
        });

        this.viewModel.notificationProperty().addListener((obs, oldVal, newVal) -> {
            this.bellImage.setVisible(newVal);
        });
    }

    private void bind() {
        avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
        employeeName.textProperty().bindBidirectional(this.viewModel.employeeNameProperty());
        employeeWorkingNumber.textProperty().bindBidirectional(this.viewModel.employeeWorkingNumberProperty());
    }

    public void openWorkersView() {
        viewHandler.openView("workers");
    }

    public void openProjects() {
        viewHandler.openView("projects");
    }

    public void openHome() {
        EmployeeRole role = this.viewModel.getEmployee().getRole();
        switch (role) {
            case WORKER -> {
                viewHandler.openView("workerHomePage");
            }
            case HR -> {
                viewHandler.openView("hrHomePage");
            }
            case PROJECT_MANAGER -> {
                viewHandler.openView("projectManagerHomePage");
            }
            case MAIN_MANAGER -> {
                viewHandler.openView("mainManagerHomePage");
            }
        }
    }


    protected void setWindow(EmployeeRole employeeRole) {
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


}
