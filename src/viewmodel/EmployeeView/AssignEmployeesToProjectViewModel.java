package viewmodel.EmployeeView;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import model.*;
import viewmodel.ViewModel;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Objects;

public class AssignEmployeesToProjectViewModel extends ViewModelWithNavigationMenu
{
    private ViewState viewState;
    private StringProperty projectName;
    private EmployeeList employeesOfManager;
    private EmployeeList employeesOfProject;
    private ObjectProperty<Employee> user;

    public AssignEmployeesToProjectViewModel(Model model, ViewState viewState) {
        super(model);
        this.viewState = viewState;
        employeesOfProject = new EmployeeList();
        employeesOfManager = new EmployeeList();
        projectName = new SimpleStringProperty("");
        user = new SimpleObjectProperty<>();
    }
    public void reset(){
        super.reset();
        load();
    }


    public void load() {
        super.load();
        user.set(model.getUser());
        Project project = viewState.getProject();
        projectName.set(project.getName());

        employeesOfProject = model.getAllEmployeesAssignedToProject(viewState.getProject().getId());
        if (user.get().getRole().equals(EmployeeRole.PROJECT_MANAGER)){
            employeesOfManager = model.getEmployeesAssignedToManager(user.get().getWorkingNumber());
            for(int i=0;i<employeesOfProject.size();i++){
                Employee employee=employeesOfProject.get(i);
                if(employee.getRole()==EmployeeRole.WORKER && !employeesOfManager.containsByWorkingNumber(employee.getWorkingNumber())){
                    employeesOfManager.addEmployee(employeesOfProject.get(i));
                }
            }
        }
        else if (user.get().getRole().equals(EmployeeRole.MAIN_MANAGER)){
            employeesOfManager = model.getAllProjectManagers();
        }

    }

    public boolean isAssigned(Employee employee) {
        if (employeesOfProject.containsByWorkingNumber(employee.getWorkingNumber())) {
            return true;
        }
        return false;
    }

    public void assignEmployee(Employee employee) {
        Project project= viewState.getProject();
        if (!employeesOfProject.containsByWorkingNumber(employee.getWorkingNumber())) {
            model.assignEmployeeToProject(employee.getWorkingNumber(), project.getId());
            employeesOfProject.addEmployee(employee);
        } else {
            model.removeEmployeeFromProject(employee.getWorkingNumber(), project.getId());
            employeesOfProject.removeByWorkingNumber(employee.getWorkingNumber());
            if(employee.getRole()==EmployeeRole.WORKER)
            {
                TaskList taskList = model.getAllTasksOfProject(project.getId());
                for(int i=0;i<taskList.size();i++)
                {
                    Task task=taskList.getTask(i);
                    if(task.getWorkers().containsByWorkingNumber(employee.getWorkingNumber()))
                    {
                        model.removeWorkerFromTask(employee.getWorkingNumber(),task.getId());
                    }
                }
            }
        }
    }



    public StringProperty getProjectName() {
        return projectName;
    }

    public EmployeeList getEmployeesOfManager() {
        return employeesOfManager;
    }


    public Employee getUser() {
        return user.get();
    }

    public ObjectProperty<Employee> userProperty() {
        return user;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }
}
