package viewmodel.WorkerView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import model.*;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.TaskView.TasksTable;
import viewmodel.ViewModel;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import javax.swing.text.html.ImageView;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Objects;

public class HrAndMainManagerProfileViewModel extends ViewModelWithNavigationMenu {
    private ViewState viewState;
    private StringProperty managerName;
    private StringProperty managerRole;
    private StringProperty managerDateOfBirth;
    private StringProperty managerPhoneNumber;
    private StringProperty managerEmail;


    public HrAndMainManagerProfileViewModel(Model model, ViewState viewState) {
        super(model);
        this.viewState = viewState;
        this.managerName = new SimpleStringProperty();
        this.managerRole = new SimpleStringProperty();
        this.managerDateOfBirth = new SimpleStringProperty();
        this.managerPhoneNumber = new SimpleStringProperty();
        this.managerEmail = new SimpleStringProperty();
    }

    public void reset(){
        super.reset();
        load();
    }
    public void load() {
        super.load();

        Employee employee = viewState.getEmployee();
        managerName.setValue(employee.getName());
        managerEmail.setValue(employee.getEmail());
        managerPhoneNumber.setValue(employee.getPhoneNumber());
        managerRole.setValue(employee.getRoleString());
        managerDateOfBirth.setValue(employee.getDob().toString());

    }


    public StringProperty managerNameProperty() {
        return managerName;
    }

    public StringProperty managerRoleProperty() {
        return managerRole;
    }


    public StringProperty managerDateOfBirthProperty() {
        return managerDateOfBirth;
    }


    public StringProperty managerPhoneNumberProperty() {
        return managerPhoneNumber;
    }


    public StringProperty managerEmailProperty() {
        return managerEmail;
    }

    public boolean isProjectManagerWoman() {
        Employee employeeManager = viewState.getEmployee();
        return Objects.equals(employeeManager.getGender(), "F");
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }
}
