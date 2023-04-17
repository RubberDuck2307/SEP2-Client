package viewmodel;

import javafx.beans.property.*;
import javafx.scene.layout.Region;
import model.Employee;
import model.Model;
import model.Project;
import model.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectsViewModel implements ViewModel
{
    private Model model;
    private StringProperty titleProperty;
    private StringProperty descriptionProperty;
    private ObjectProperty<LocalDate> deadlineProperty;
    private StringProperty employeeNameProperty;
    private StringProperty managerNameProperty;
    private StringProperty employeePositionProperty;
    private ArrayList<Employee> employeesList;

    public ProjectsViewModel(Model model) {
        this.model = model;
        titleProperty = new SimpleStringProperty();
        descriptionProperty = new SimpleStringProperty();
        deadlineProperty = new SimpleObjectProperty<>();
        employeeNameProperty = new SimpleStringProperty();
        managerNameProperty = new SimpleStringProperty();
        employeePositionProperty = new SimpleStringProperty();
        this.employeesList = new ArrayList<>();
    }

    public StringProperty getMangerNameProperty() {
        return managerNameProperty;
    }

    public StringProperty getEmployeePositionProperty() {
        return employeePositionProperty;
    }

    public StringProperty getEmployeeNameProperty() {
        return employeeNameProperty;
    }

    public ObjectProperty<LocalDate> getDeadlineProperty() {
        return deadlineProperty;
    }

    public void setDeadlineProperty(LocalDate deadlineProperty) {
        this.deadlineProperty.set(deadlineProperty);
    }

    public StringProperty getTitleProperty() {
        return titleProperty;
    }

    public StringProperty getDescriptionProperty() {
        return descriptionProperty;
    }

    //public ArrayList<Employee> getEmployeesWithAccess() {
      //  Project project = null;
        //return new ArrayList<>(project.getEmployeesWithAccess());
    //}
}
