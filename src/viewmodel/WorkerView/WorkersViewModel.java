package viewmodel.WorkerView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import model.*;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.util.Objects;

public class WorkersViewModel extends ViewModelWithNavigationMenu {

  private ObjectProperty<Employee> selectedEmployee;
  private ViewState viewState;
  private ObservableList<WorkersTable> workersTables;

  private EmployeeList employeeList;
  private ObservableList<Employee> employees;

  public WorkersViewModel(Model model, ViewState viewState)
  {
    super(model);
    this.viewState = viewState;
    this.workersTables = FXCollections.observableArrayList();
    this.employees = FXCollections.observableArrayList();
    this.selectedEmployee = new SimpleObjectProperty<>();

    this.avatarPic=new SimpleObjectProperty<>();
  }


  public void reset() {
    super.reset();
    load();
  }


  public void load()
  {
    super.load();
   employeeList = model.getAllEmployees();
    workersTables.clear();
    employees.clear();
    for (int i = 0; i < employeeList.size(); i++)
    {
      workersTables.add(new viewmodel.WorkerView.WorkersTable(employeeList.get(i)));
      employees.add(employeeList.get(i));
    }

  }

  public void delete(Employee employee)
  {
    workersTables.remove(employee);
    employees.remove(employee);
    employeeList.removeByWorkingNumber(employee.getWorkingNumber());
    selectedEmployee.setValue(employee);
    model.deleteEmployeeByWorkingNumber(employee.getWorkingNumber());
  }

  public void chooseWorker(int workingNumber)
  {
    Employee employee = model.getEmployeeByWorkingNumber(workingNumber);
    viewState.setEmployee(employee);
    workersTables.clear();
  }

  public void chooseWorker(Employee employee)
  {
    viewState.setEmployee(employee);
    selectedEmployee.setValue(employee);
    workersTables.clear();
  }
  public boolean isMainManager(int workingNumber){
    Employee employee = model.getEmployeeByWorkingNumber(workingNumber);
    return employee.getRole() == EmployeeRole.PROJECT_MANAGER;
  }
  public boolean isWorker(int workingNumber){
    Employee employee = model.getEmployeeByWorkingNumber(workingNumber);
    return employee.getRole() == EmployeeRole.WORKER;
  }
  public Employee getSelectedEmployee()
  {
    return selectedEmployee.get();
  }
  public ObjectProperty<Employee> selectedEmployeeProperty()
  {
    return selectedEmployee;
  }




  public ObservableList<viewmodel.WorkerView.WorkersTable> getWorkersTable(){return workersTables;}
  public ObservableList<Employee> getEmployeess()
  {
    return employees;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    super.propertyChange(evt);
  }
}
