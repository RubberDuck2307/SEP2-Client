package viewmodel.WorkerView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import model.*;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.ViewModel;
import viewmodel.ViewState;

import java.util.ArrayList;
import java.util.Objects;

public class WorkerProfileViewModel implements ViewModel
{
  private Model model;
  private ViewState viewState;
  private ObjectProperty<Employee> employee;
  private ObjectProperty<Image> avatarPic;
  private StringProperty employeeName;
  private StringProperty employeeWorkingNumber;
  private ObservableList<ProjectsTable> currentProjectsTable;
  private StringProperty projectTitle;
  private StringProperty projectDeadline;
  private ProjectList projectList;

  private ObservableList<TasksTableForWorkerProfile> tasksTable;
  private StringProperty taskTitle;
  private StringProperty taskStatus;
  private StringProperty taskProjectName;
  private TaskList taskList;


  private StringProperty workerName;

  private StringProperty workerRole;

  private StringProperty workerDateOfBirth;

  private StringProperty workerPhoneNumber;

  private StringProperty workerEmail;
  private StringProperty workerManagers;
  public WorkerProfileViewModel(Model model, ViewState viewState)
  {
    this.employeeName=new SimpleStringProperty();
    this.employeeWorkingNumber=new SimpleStringProperty();
    this.employee=new SimpleObjectProperty<>();
    this.avatarPic=new SimpleObjectProperty<>();
    this.model = model;
    this.viewState = viewState;


    this.workerName = new SimpleStringProperty();
    this.workerRole = new SimpleStringProperty();
    this.workerDateOfBirth = new SimpleStringProperty();
    this.workerPhoneNumber = new SimpleStringProperty();
    this.workerEmail = new SimpleStringProperty();
    this.workerManagers = new SimpleStringProperty();

    this.currentProjectsTable = FXCollections.observableArrayList();
    this.projectDeadline = new SimpleStringProperty();
    this.projectTitle = new SimpleStringProperty();

    this.tasksTable = FXCollections.observableArrayList();
    this.taskStatus = new SimpleStringProperty();
    this.taskTitle = new SimpleStringProperty();
    this.taskProjectName = new SimpleStringProperty();
  }
  public void load()
  {
    employee.setValue(model.getUser());
    setAvatarPicture();
    employeeName.setValue(model.getUser().getName());
    employeeWorkingNumber.setValue(model.getUser().getWorkingNumber().toString());

    Employee employee = viewState.getEmployee();
    workerName.setValue(employee.getName());
    workerEmail.setValue(employee.getEmail());
    workerPhoneNumber.setValue(employee.getPhoneNumber());
    workerRole.setValue(employee.getRole().toString());
    workerDateOfBirth.setValue(employee.getDob().toString());
    EmployeeList managersList = model.getAllWorkersManagersByWorkerWorkingNumber(employee.getWorkingNumber());
    String managers = "";
    for(int i = 0; i<managersList.size(); i++){
      if(i==0){
        managers = managersList.get(i).getName();
      }
      else{
        managers = managers + ", " + managersList.get(i).getName();
      }

    }
    if(Objects.equals(managers, "")){
      workerManagers.setValue("This worker is not assigned to any manager.");
    }
    else{
      workerManagers.setValue(managers);
    }
    projectList = model.getAllProjectsByWorkingNumber(employee.getWorkingNumber());
    currentProjectsTable.clear();
    for (int i = 0; i < projectList.size(); i++)
    {
      currentProjectsTable.add(new ProjectsTable(projectList.get(i)));
    }
    taskList = model.getAllTasksByUserId(employee.getWorkingNumber());
    tasksTable.clear();
    for (int i = 0; i < taskList.size(); i++)
    {
      tasksTable.add(new TasksTableForWorkerProfile(taskList.getTask(i),model.getProjectById(taskList.getTask(i).getProjectId())));
    }

  }
  public void reset(){
    load();
    Employee employee = viewState.getEmployee();
  }

  public ObservableList<ProjectsTable> getCurrentProjectsTableTable(){return currentProjectsTable;}
  public ObservableList<TasksTableForWorkerProfile> getTaskTable(){return tasksTable;}

  public String getWorkerName()
  {
    return workerName.get();
  }

  public String getWorkerManagers()
  {
    return workerManagers.get();
  }

  public StringProperty workerManagersProperty()
  {
    return workerManagers;
  }

  public StringProperty workerNameProperty()
  {
    return workerName;
  }

  public String getWorkerRole()
  {
    return workerRole.get();
  }

  public StringProperty workerRoleProperty()
  {
    return workerRole;
  }

  public String getWorkerDateOfBirth()
  {
    return workerDateOfBirth.get();
  }

  public StringProperty workerDateOfBirthProperty()
  {
    return workerDateOfBirth;
  }

  public String getWorkerPhoneNumber()
  {
    return workerPhoneNumber.get();
  }

  public StringProperty workerPhoneNumberProperty()
  {
    return workerPhoneNumber;
  }

  public String getWorkerEmail()
  {
    return workerEmail.get();
  }

  public StringProperty workerEmailProperty()
  {
    return workerEmail;
  }

  public boolean isProjectManagerWoman(){
    Employee employeeManager = viewState.getEmployee();
    return Objects.equals(employeeManager.getGender(), "F");
  }

  public StringProperty getEmployeeName()
  {
    return employeeName;
  }

  public StringProperty getEmployeeWorkingNumber()
  {
    return employeeWorkingNumber;
  }
  public ObjectProperty<Employee> employeePropertyProperty() {
    return employee;
  }
  public Employee getEmployeeProperty() {
    return employee.get();
  }



  public ObjectProperty<Image> avatarPicProperty()
  {
    return avatarPic;
  }
  public boolean isWoman(){
    return Objects.equals(employee.getValue().getGender(), "F");
  }
  public void setAvatarPicture(){
    if(isWoman()){
      avatarPic.setValue(new Image("/icons/woman-avatar.png"));
    }
    else{
      avatarPic.setValue(new Image("/icons/man-avatar.png"));
    }
  }
}
