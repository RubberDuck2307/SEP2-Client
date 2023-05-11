package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import model.*;

import java.util.ArrayList;

public class AssignEmployeesToProjectViewModel implements ViewModel {
    private ViewState viewState;
    private Model model;
    private StringProperty userName;
    private StringProperty userNumber;
    private StringProperty projectName;
    private EmployeeList employeesOfManager;
    private EmployeeList employeesOfProject;
    private ArrayList<Employee> asigneeList;
    private ObservableList<Employee> employees;
    private ObjectProperty<Employee> user;

    public AssignEmployeesToProjectViewModel(Model model, ViewState viewState) {
        this.viewState = viewState;
        this.model = model;
        employeesOfProject = new EmployeeList();
        employeesOfManager = new EmployeeList();
        projectName = new SimpleStringProperty();
        user = new SimpleObjectProperty<>();
        userName = new SimpleStringProperty();
        userNumber = new SimpleStringProperty();
    }

    public void load() {
        user.set(model.getUser());
        Project project = viewState.getProject();
        projectName.set(project.getName());
        if (user.get().getRole().equals(EmployeeRole.PROJECT_MANAGER)){
            employeesOfManager = model.getEmployeesAssignedToManager(user.get().getWorkingNumber());
        }
        else if (user.get().getRole().equals(EmployeeRole.MAIN_MANAGER)){
            employeesOfManager = model.getAllProjectManagers();
        }
        employeesOfProject = model.getAllEmployeesAssignedToProject(viewState.getProject().getId());
        userName.set(user.get().getName());
        userNumber.set(user.get().getWorkingNumber().toString());

    }

    public boolean isAssigned(Employee employee) {
        if (employeesOfProject.containsByWorkingNumber(employee.getWorkingNumber())) {
            return true;
        }
        return false;
    }

    public void assignEmployee(Employee employee) {
        if (!employeesOfProject.containsByWorkingNumber(employee.getWorkingNumber())) {
            model.assignEmployeeToProject(employee.getWorkingNumber(), viewState.getProject().getId());
            employeesOfProject.addEmployee(employee);
        } else {
            model.removeEmployeeFromProject(employee.getWorkingNumber(), viewState.getProject().getId());
            employeesOfProject.removeByWorkingNumber(employee.getWorkingNumber());
        }
    }


    public ObservableList<Employee> getEmployees() {
        return employees;
    }

    public StringProperty getProjectName() {
        return projectName;
    }

    public EmployeeList getEmployeesOfManager() {
        return employeesOfManager;
    }

    public EmployeeList getEmployeesOfProject() {
        return employeesOfProject;
    }

    public Employee getUser() {
        return user.get();
    }

    public ObjectProperty<Employee> userProperty() {
        return user;
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public String getUserNumber() {
        return userNumber.get();
    }

    public StringProperty userNumberProperty() {
        return userNumber;
    }
}
