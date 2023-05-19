package viewmodel.EmployeeView;

import javafx.beans.property.*;
import javafx.scene.image.Image;
import model.*;
import viewmodel.ViewModel;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class AssignWorkersToTaskViewModel extends ViewModelWithNavigationMenu
{
    private ViewState viewState;
    private StringProperty taskName;
    private EmployeeList employeesOfManager;
    private EmployeeList employeesOfTask;
    private EmployeeList newEmployeesOfTask;
    private EmployeeList employeesOfProject;
    private ObjectProperty<Employee> user;

    public AssignWorkersToTaskViewModel(Model model, ViewState viewState) {
        super(model);
        this.viewState = viewState;
        employeesOfTask = new EmployeeList();
        employeesOfManager = new EmployeeList();
        taskName = new SimpleStringProperty();
        user= new SimpleObjectProperty<>();
    }

    public void reset(){
        super.reset();
        load();
    }
    public void load() {
        super.load();
        taskName.set(viewState.getTask().getName());
        user.set(model.getUser());
        employeesOfManager = model.getEmployeesAssignedToManager(user.get().getWorkingNumber());
        employeesOfProject = model.getAllEmployeesAssignedToProject(viewState.getProject().getId());
        employeesOfTask = new EmployeeList();
        newEmployeesOfTask = model.getEmployeesOfTask(viewState.getTask().getId());
        for(int i=0;i<employeesOfManager.size();i++)
        {
            if(employeesOfProject.containsByWorkingNumber(employeesOfManager.get(i).getWorkingNumber()))
            {
                employeesOfTask.addEmployee(model.getEmployeeByWorkingNumber(employeesOfManager.get(i).getWorkingNumber()));
            }
        }
    }

    public boolean isAssigned(Employee employee) {
        EmployeeList assignedEmployees=model.getEmployeesOfTask(viewState.getTask().getId());
        if (assignedEmployees.containsByWorkingNumber(employee.getWorkingNumber())) {
            return true;
        }
        return false;
    }

    public void assignEmployee(Employee employee) {
        if (!newEmployeesOfTask.containsByWorkingNumber(employee.getWorkingNumber())) {
            model.assignWorkerToTask(employee.getWorkingNumber(), viewState.getTask().getId());
            newEmployeesOfTask.addEmployee(employee);
        }
        else {
          model.removeWorkerFromTask(employee.getWorkingNumber(), viewState.getTask().getId());
          newEmployeesOfTask.removeByWorkingNumber(employee.getWorkingNumber());
        }
    }

    public StringProperty getTaskName() {
        return taskName;
    }


    public EmployeeList getEmployeesOfTask() {
        return employeesOfTask;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }
}
