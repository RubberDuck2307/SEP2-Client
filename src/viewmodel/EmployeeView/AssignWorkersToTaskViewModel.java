package viewmodel.EmployeeView;

import javafx.beans.property.*;
import model.*;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;

public class AssignWorkersToTaskViewModel extends ViewModelWithNavigationMenu
{
    private ViewState viewState;
    private StringProperty taskName;

    private EmployeeList employeesOfTask;
    private EmployeeList workersOfTask;
    private EmployeeList currentEmployeesOfTask;
    private ObjectProperty<Employee> user;

    public AssignWorkersToTaskViewModel(Model model, ViewState viewState) {
        super(model);
        this.viewState = viewState;
        workersOfTask = new EmployeeList();
        taskName = new SimpleStringProperty();
        user= new SimpleObjectProperty<>();
    }

    public void reset(){
        super.reset();
        load();
    }
    public void load() {
        super.load();
        Task task = viewState.getTask();
        taskName.set(task.getName());
        user.set(model.getUser());
        employeesOfTask = model.getAllEmployeesAssignedToProject(viewState.getProject().getId());
        workersOfTask= model.getEmployeesOfTask(task.getId());
        for(int i = 0; i< employeesOfTask.size(); i++){
            Employee employee= employeesOfTask.get(i);
            if(employee.getRole()==EmployeeRole.WORKER && !workersOfTask.containsByWorkingNumber(employee.getWorkingNumber())){
                workersOfTask.addEmployee(employeesOfTask.get(i));
            }
        }
        currentEmployeesOfTask = model.getEmployeesOfTask(viewState.getTask().getId());
    }

    public boolean isAssigned(Employee employee) {
        EmployeeList assignedEmployees=model.getEmployeesOfTask(viewState.getTask().getId());
        if (assignedEmployees.containsByWorkingNumber(employee.getWorkingNumber())) {
            return true;
        }
        return false;
    }

    public void assignEmployee(Employee employee) {
        if (!currentEmployeesOfTask.containsByWorkingNumber(employee.getWorkingNumber())) {
            model.assignWorkerToTask(employee.getWorkingNumber(), viewState.getTask().getId());
            currentEmployeesOfTask.addEmployee(employee);
        }
        else {
          model.removeWorkerFromTask(employee.getWorkingNumber(), viewState.getTask().getId());
          currentEmployeesOfTask.removeByWorkingNumber(employee.getWorkingNumber());
        }
    }

    public StringProperty getTaskName() {
        return taskName;
    }


    public EmployeeList getWorkersOfTask() {
        return workersOfTask;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }
}
