package viewmodel.WorkerView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeList;
import model.Model;
import model.ProjectList;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.util.Objects;

public class ProjectManagerProfileViewModel extends ViewModelWithNavigationMenu
{
  private ViewState viewState;
  private StringProperty managerName;
  private StringProperty managerRole;
  private StringProperty managerDateOfBirth;
  private StringProperty managerPhoneNumber;
  private StringProperty managerEmail;
  private ObservableList<WorkersTable> workersTables;
  private EmployeeList employeeList;
  private ObservableList<ProjectsTable> currentProjectsTable;
  private ProjectList projectList;


  public ProjectManagerProfileViewModel(Model model, ViewState viewState)
  {
    super(model);
    this.viewState = viewState;
    this.workersTables = FXCollections.observableArrayList();

    this.managerName = new SimpleStringProperty();
    this.managerRole = new SimpleStringProperty();
    this.managerDateOfBirth = new SimpleStringProperty();
    this.managerPhoneNumber = new SimpleStringProperty();
    this.managerEmail = new SimpleStringProperty();

    this.currentProjectsTable = FXCollections.observableArrayList();
  }


  public void reset(){
    super.reset();
    load();
  }
  public void load()
  {
    super.load();

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
      workersTables.add(new viewmodel.WorkerView.WorkersTable(employeeList.get(i)));
    }
    currentProjectsTable.clear();
    for (int i = 0; i < projectList.size(); i++)
    {
      currentProjectsTable.add(new ProjectsTable(projectList.get(i)));
    }
  }
  public ObservableList<viewmodel.WorkerView.WorkersTable> getWorkersTable(){return workersTables;}
  public ObservableList<ProjectsTable> getCurrentProjectsTableTable(){return currentProjectsTable;}
  public StringProperty managerNameProperty()
  {
    return managerName;
  }
  public StringProperty managerRoleProperty()
  {
    return managerRole;
  }
  public StringProperty managerDateOfBirthProperty()
  {
    return managerDateOfBirth;
  }
  public StringProperty managerPhoneNumberProperty()
  {
    return managerPhoneNumber;
  }
  public StringProperty managerEmailProperty()
  {
    return managerEmail;
  }
  public boolean isProjectManagerWoman(){
    Employee employeeManager = viewState.getEmployee();
    return Objects.equals(employeeManager.getGender(), "F");
  }


  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    super.propertyChange(evt);
  }
}
