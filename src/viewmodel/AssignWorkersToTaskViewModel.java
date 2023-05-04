package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class AssignWorkersToTaskViewModel implements ViewModel
{
  private ViewState viewState;
  private Model model;
  private StringProperty projectName;
  private EmployeeList employeeList;
  private ArrayList<Employee> asigneeList;
  private ObservableList<Employee> employees;
  private ObservableList<WorkersWithCheckboxTable> workersWithCheckboxTableObservableList;
  public AssignWorkersToTaskViewModel(Model  model, ViewState viewState){
    this.viewState = viewState;
    this.model = model;
    asigneeList =new ArrayList<>();
    employeeList=new EmployeeList();
    //employeeList.addEmployee(new Employee(112,"Gabi", LocalDate.now(),"123", "M", EmployeeRole.WORKER, "gabi@rema.1000"));
    //employeeList.addEmployee(new Employee(113,"Gigi", LocalDate.now(),"1234", "M", EmployeeRole.WORKER, "gigi@rema.1000"));
    employees = FXCollections.observableArrayList();
    workersWithCheckboxTableObservableList=FXCollections.observableArrayList();
    projectName=new SimpleStringProperty();
    load();
  }

  public void load()
  {
    Project project = viewState.getProject();
    projectName.set(project.getName());
    //TODO get the employee list of the current person
    //employeeList = new EmployeeList();
    ArrayList<Employee> arrayList=model.getEmployeesAssignedToManager(4);
    //TODO decide between arrayList and EmployeeList
    workersWithCheckboxTableObservableList.clear();
    employees.clear();
    for (int i = 0; i < arrayList.size(); i++) {
      employeeList.addEmployee(arrayList.get(i));
      workersWithCheckboxTableObservableList.add(new WorkersWithCheckboxTable(employeeList.get(i)));
      employees.add(employeeList.get(i));
    }
    /*tasksTables.clear();
    for (int i = 0; i < taskList.size(); i++)
    {
      tasksTables.add(new TasksTable(taskList.getTask(i)));
    }*/

  }

  public void checkAsignee(Employee employee){
    if(asigneeList.contains(employee)){
      asigneeList.remove(employee);
    }
    else{
      asigneeList.add(employee);
    }
  }

  public void assignWorkers(){
    Long taskID=viewState.getTask().getId();
    for(int i=0;i<asigneeList.size();i++){
      System.out.println(taskID+" ** "+ asigneeList.get(i));
      //TODO use the method
      model.assignWorkerToTask(asigneeList.get(i).getWorkingNumber(), taskID);
    }
  }

  public ObservableList<Employee> getEmployees()
  {
    return employees;
  }

  public StringProperty getProjectName()
  {
    return projectName;
  }

  public ObservableList<WorkersWithCheckboxTable> getWorkersWithCheckboxTableObservableList()
  {
    return workersWithCheckboxTableObservableList;
  }
}
