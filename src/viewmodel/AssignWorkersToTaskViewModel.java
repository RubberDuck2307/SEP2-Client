package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class AssignWorkersToTaskViewModel implements ViewModel {
    private ViewState viewState;
    private Model model;
    private StringProperty projectName;
    private EmployeeList employeesOfManager;
    private EmployeeList employeesOfTask;
    private ArrayList<Employee> asigneeList;
    private ObservableList<Employee> employees;
    private StringProperty name, workingNumber;

    public AssignWorkersToTaskViewModel(Model model, ViewState viewState) {
        this.viewState = viewState;
        this.model = model;
        employeesOfTask = new EmployeeList();
        employeesOfManager = new EmployeeList();
        projectName = new SimpleStringProperty();
        name = new SimpleStringProperty();
        workingNumber = new SimpleStringProperty();
    }

    public void load() {
        Project project = viewState.getProject();
        projectName.set(project.getName());
        //TODO change employees of manager to fit the current project manager
        employeesOfManager = model.getEmployeesAssignedToManager(4);
        employeesOfTask = model.getEmployeesOfTask(viewState.getTask().getId());

    }

    public boolean isAssigned(Employee employee) {
        if (employeesOfTask.containsByWorkingNumber(employee.getWorkingNumber())) {
            return true;
        }
        return false;
    }

    public void assignEmployee(Employee employee) {
        if (!employeesOfTask.containsByWorkingNumber(employee.getWorkingNumber())) {
            model.assignWorkerToTask(employee.getWorkingNumber(), viewState.getTask().getId());
            employeesOfTask.addEmployee(employee);
        }
        else {
          model.removeWorkerFromTask(employee.getWorkingNumber(), viewState.getTask().getId());
          employeesOfTask.removeByWorkingNumber(employee.getWorkingNumber());
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

    public EmployeeList getEmployeesOfTask() {
        return employeesOfTask;
    }
    public String getName()
    {
        return name.get();
    }
    public StringProperty nameProperty()
    {
        return name;
    }
    public String getWorkingNumber()
    {
        return workingNumber.get();
    }
    public StringProperty workingNumberProperty()
    {
        return workingNumber;
    }
}
