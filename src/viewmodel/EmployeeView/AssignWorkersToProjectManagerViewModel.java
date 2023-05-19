package viewmodel.EmployeeView;

import javafx.beans.property.*;
import javafx.scene.image.Image;
import model.Employee;
import model.EmployeeList;
import model.Model;
import viewmodel.ViewModel;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class AssignWorkersToProjectManagerViewModel extends ViewModelWithNavigationMenu
{
    private ViewState viewState;
    private EmployeeList employeesOfManager;
    private EmployeeList employees;
    private StringProperty managerName;

    public AssignWorkersToProjectManagerViewModel(Model model, ViewState viewState) {
        super(model);
        this.viewState = viewState;
        this.model = model;
        employeesOfManager = new EmployeeList();
        employees = new EmployeeList();
        managerName = new SimpleStringProperty();
    }

    public void load() {
        super.load();
        employeesOfManager=model.getEmployeesAssignedToManager(viewState.getEmployee().getWorkingNumber());
        employees = model.getAllWorkers();
        managerName.set(viewState.getEmployee().getName());

    }

    public void reset(){
        super.reset();
        employeeName.set(viewState.getEmployee().getName());
        load();
    }
    public boolean isAssigned(Employee employee) {
        if (employeesOfManager.containsByWorkingNumber(employee.getWorkingNumber())) {
            return true;
        }
        return false;
    }

    public void assignEmployee(Employee employee) {
        if (!employeesOfManager.containsByWorkingNumber(employee.getWorkingNumber())) {
            model.assignWorkerToManager(viewState.getEmployee().getWorkingNumber(), employee.getWorkingNumber());
            employeesOfManager.addEmployee(employee);
        }
        else {
          model.removeWorkerFromManager(viewState.getEmployee().getWorkingNumber(), employee.getWorkingNumber());
          employeesOfManager.removeByWorkingNumber(employee.getWorkingNumber());
        }
    }

    public StringProperty managerNameProperty() {
        return managerName;
    }

    public EmployeeList getEmployees()
    {
        return employees;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }

}
