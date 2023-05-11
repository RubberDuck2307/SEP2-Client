package viewmodel.WorkerView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.*;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.TaskView.TasksTable;
import viewmodel.ViewModel;
import viewmodel.ViewState;

import java.util.ArrayList;

public class ProjectManagerProfileViewModel implements ViewModel
{
  private Model model;
  private ViewState viewState;
  private ObjectProperty<Employee> employee;
  private StringProperty employeeName;
  private StringProperty employeeWorkingNumber;
  private StringProperty managerName;
  private StringProperty managerRole;
  private StringProperty managerDateOfBirth;
  private StringProperty managerPhoneNumber;
  private StringProperty managerEmail;

  private ObservableList<WorkersTable> workersTables;
  private StringProperty name;
  private IntegerProperty number;
  private StringProperty email;
  private EmployeeList employeeList;

  private ObservableList<ProjectsTable> currentProjectsTable;
  private StringProperty projectTitle;
  private StringProperty projectDeadline;
  private ProjectList projectList;

  public ProjectManagerProfileViewModel(Model model, ViewState viewState)
  {
    this.employeeName=new SimpleStringProperty();
    this.employeeWorkingNumber=new SimpleStringProperty();
    this.employee=new SimpleObjectProperty<>();
    this.model = model;
    this.viewState = viewState;
    this.workersTables = FXCollections.observableArrayList();
    this.name = new SimpleStringProperty();
    this.number = new SimpleIntegerProperty();
    this.email = new SimpleStringProperty();

    this.managerName = new SimpleStringProperty();
    this.managerRole = new SimpleStringProperty();
    this.managerDateOfBirth = new SimpleStringProperty();
    this.managerPhoneNumber = new SimpleStringProperty();
    this.managerEmail = new SimpleStringProperty();

    this.currentProjectsTable = FXCollections.observableArrayList();
    this.projectDeadline = new SimpleStringProperty();
    this.projectTitle = new SimpleStringProperty();
  }
  public void load()
  {
    employee.setValue(model.getUser());
    employeeName.setValue(model.getUser().getName());
    employeeWorkingNumber.setValue(model.getUser().getWorkingNumber().toString());

    Employee employee = viewState.getEmployee();
    managerName.setValue(employee.getName());
    managerEmail.setValue(employee.getEmail());
    managerPhoneNumber.setValue(employee.getPhoneNumber());
    managerRole.setValue(employee.getRole().toString());
    managerDateOfBirth.setValue(employee.getDob().toString());


    projectList = model.getAllProjectsByWorkingNumber(employee.getWorkingNumber());
    employeeList = model.getEmployeesAssignedToManager(employee.getWorkingNumber());
    workersTables.clear();
    for (int i = 0; i < employeeList.size(); i++)
    {
      workersTables.add(new viewmodel.WorkerView.WorkersTable(employeeList.getEmployee(i)));
    }
    currentProjectsTable.clear();
    for (int i = 0; i < projectList.size(); i++)
    {
      currentProjectsTable.add(new ProjectsTable(projectList.get(i)));
    }

  }
  public ObservableList<viewmodel.WorkerView.WorkersTable> getWorkersTable(){return workersTables;}
  public ObservableList<ProjectsTable> getCurrentProjectsTableTable(){return currentProjectsTable;}

  public String getManagerName()
  {
    return managerName.get();
  }

  public StringProperty managerNameProperty()
  {
    return managerName;
  }

  public String getManagerRole()
  {
    return managerRole.get();
  }

  public StringProperty managerRoleProperty()
  {
    return managerRole;
  }

  public String getManagerDateOfBirth()
  {
    return managerDateOfBirth.get();
  }

  public StringProperty managerDateOfBirthProperty()
  {
    return managerDateOfBirth;
  }

  public String getManagerPhoneNumber()
  {
    return managerPhoneNumber.get();
  }

  public StringProperty managerPhoneNumberProperty()
  {
    return managerPhoneNumber;
  }

  public String getManagerEmail()
  {
    return managerEmail.get();
  }

  public StringProperty managerEmailProperty()
  {
    return managerEmail;
  }
  public boolean displayAddButton(){
    return employee.getValue().getRole() == EmployeeRole.HR;
  }
  public StringProperty getEmployeeName()
  {
    return employeeName;
  }

  public StringProperty getEmployeeWorkingNumber()
  {
    return employeeWorkingNumber;
  }
}
