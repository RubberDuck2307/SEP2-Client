package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeList;
import model.Model;
import model.Project;

import java.util.ArrayList;

public class AssignEmployeesToProjectViewModel implements ViewModel {
    private ViewState viewState;
    private Model model;
    private StringProperty projectName;
    private EmployeeList employeesOfManager;
    private EmployeeList employeesOfProject;
    private ArrayList<Employee> asigneeList;
    private ObservableList<Employee> employees;

    public AssignEmployeesToProjectViewModel(Model model, ViewState viewState) {
        this.viewState = viewState;
        this.model = model;
        employeesOfProject = new EmployeeList();
        employeesOfManager = new EmployeeList();
        projectName = new SimpleStringProperty();
    }

    public void load() {
        Project project = viewState.getProject();
        projectName.set(project.getName());
        //TODO change employees of manager to fit the current main/project manager
        employeesOfManager = model.getEmployeesAssignedToManager(4);
        employeesOfProject = model.getAllEmployeesAssignedToProject(viewState.getProject().getId());

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
        }
        else {
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
}
