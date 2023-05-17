package viewmodel.WorkerView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import model.*;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.ViewModel;
import viewmodel.ViewState;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class WorkerHomeViewModel implements ViewModel
{
  private Model model;
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

  public WorkerHomeViewModel(Model model, ViewState viewState)
  {
    this.employeeName=new SimpleStringProperty();
    this.employeeWorkingNumber=new SimpleStringProperty();
    this.employee=new SimpleObjectProperty<>();
    this.avatarPic=new SimpleObjectProperty<>();
    this.model = model;


    this.workerName = new SimpleStringProperty();

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

    Employee worker = model.getUser();
    headline();

    EmployeeList managersList = model.getAllWorkersManagersByWorkerWorkingNumber(worker.getWorkingNumber());
    String managers = "";
    for(int i = 0; i<managersList.size(); i++){
      if(i==0){
        managers = managersList.get(i).getName();
      }
      else{
        managers = managers + ", " + managersList.get(i).getName();
      }

    }
    projectList = model.getAllProjectsByWorkingNumber(worker.getWorkingNumber());
    currentProjectsTable.clear();
    for (int i = 0; i < projectList.size(); i++)
    {
      currentProjectsTable.add(new ProjectsTable(projectList.get(i)));
    }
    taskList = model.getAllTasksByUserId(worker.getWorkingNumber());
    tasksTable.clear();
    for (int i = 0; i < taskList.size(); i++)
    {
      tasksTable.add(new TasksTableForWorkerProfile(taskList.getTask(i),model.getProjectById(taskList.getTask(i).getProjectId())));
    }

  }
  
  public void headline()
  {
    Employee worker = model.getUser();
    LocalTime time = LocalTime.now();
    int hour = time.getHour();
    switch (hour)
    {
      case 6, 7, 8, 9, 10, 11, 12 -> workerName.setValue("Good morning, " + worker.getName() + "!");
      case 13, 14, 15, 16, 17 -> workerName.setValue("Good afternoon, " + worker.getName() + "!");
      case 18, 19, 20, 21, 22, 23 -> workerName.setValue("Good evening, " + worker.getName() + "!");
      case 0, 1, 2, 3, 4, 5 -> workerName.setValue("Good night, " + worker.getName() + "!");
    }
  }

  public ObservableList<ProjectsTable> getCurrentProjectsTableTable(){return currentProjectsTable;}
  public ObservableList<TasksTableForWorkerProfile> getTaskTable(){return tasksTable;}

  public String getWorkerName()
  {
    return workerName.get();
  }


  public StringProperty workerNameProperty()
  {
    return workerName;
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
