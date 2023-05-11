package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.*;
import viewmodel.TaskView.CommentsTable;
import viewmodel.TaskView.TasksTable;
import viewmodel.TaskView.WorkersTable;

import java.time.LocalDate;
import java.util.ArrayList;

public class TasksViewModel implements ViewModel
{
  private Model model;
  //tasks
  private StringProperty employeeName;
  private StringProperty employeeWorkingNumber;
  private TaskList taskList;
  private EmployeeList employeeList;
  private StringProperty error;
  private StringProperty projectName;
  private StringProperty taskName;
  private StringProperty taskDescription;
  private ObjectProperty<Employee> employee;
  private ObservableList<Task> tasks;
  private ObservableList<TasksTable> tasksTables;
  private ObservableList<CommentsTable> commentsTables;
  private ObservableList<WorkersTable> workersTables;
  private StringProperty name;
  private IntegerProperty number;
  private StringProperty position;
  private ViewState viewState;
  private BooleanProperty isTaskSelected;

  public TasksViewModel(Model model, ViewState viewState)
  {
    this.error = new SimpleStringProperty();
    this.model = model;
    this.employeeName=new SimpleStringProperty();
    this.employeeWorkingNumber=new SimpleStringProperty();
    this.employee=new SimpleObjectProperty<>();
    this.tasks = FXCollections.observableArrayList();
    this.viewState = viewState;
    this.taskList = new TaskList();
    this.employeeList = new EmployeeList();
    //employeeList.addEmployee(viewState.getEmployee());
    this.projectName = new SimpleStringProperty();
    this.taskName = new SimpleStringProperty();
    this.taskDescription = new SimpleStringProperty();
    this.tasksTables = FXCollections.observableArrayList();
    this.commentsTables = FXCollections.observableArrayList();
    this.workersTables = FXCollections.observableArrayList();
    this.name = new SimpleStringProperty();
    this.position = new SimpleStringProperty();
    this.number = new SimpleIntegerProperty();
    this.isTaskSelected = new SimpleBooleanProperty();
  }

  public void load()
  {
    isTaskSelected.set(false);
    Project project = viewState.getProject();
    employee.setValue(model.getUser());
    employeeName.setValue(model.getUser().getName());
    employeeWorkingNumber.setValue(model.getUser().getWorkingNumber().toString());
    taskList = model.getAllTasksOfProject(project.getId());
    taskName.setValue("Description");
    projectName.setValue(project.getName());
    taskDescription.setValue("Select a task to see the description and comments");
    tasksTables.clear();
    tasks.clear();
    for (int i = 0; i < taskList.size(); i++)
    {
      tasksTables.add(new TasksTable(taskList.getTask(i)));
      tasks.add(taskList.getTask(i));
    }

  }

  public ObservableList<TasksTable> getAll()
  {
    return tasksTables;
  }

  public ObjectProperty<Employee> employeeProperty()
  {
    return employee;
  }

  public Employee getEmployee()
  {
    return employee.get();
  }

  public StringProperty getEmployeeName()
  {
    return employeeName;
  }

  public StringProperty getEmployeeWorkingNumber()
  {
    return employeeWorkingNumber;
  }

  public ObservableList<WorkersTable> getWorkersTables(){return workersTables;}

  public StringProperty getError()
  {
    return error;
  }

  public String getProjectName()
  {
    return projectName.get();
  }

  public StringProperty projectNameProperty()
  {
    return projectName;
  }

  public String getTaskName()
  {
    return taskName.get();
  }

  public StringProperty taskNameProperty()
  {
    return taskName;
  }

  public String getTaskDescription()
  {
    return taskDescription.get();
  }

  public StringProperty taskDescriptionProperty()
  {
    return taskDescription;
  }

  public String getName()
  {
    return name.get();
  }

  public StringProperty nameProperty()
  {
    return name;
  }

  public int getNumber()
  {
    return number.get();
  }

  public IntegerProperty numberProperty()
  {
    return number;
  }

  public String getPosition()
  {
    return position.get();
  }

  public StringProperty positionProperty()
  {
    return position;
  }

  public ObservableList<Task> getTasks()
  {
    return tasks;
  }

  public void chooseTask(Long id)
  {
    Task task=taskList.getTaskById(id);
    taskName.setValue(task.getName());
    taskDescription.setValue(task.getDescription());
    System.out.println(task);
    viewState.setTask(task);
    EmployeeList employeeList = model.getEmployeesOfTask(id);
    workersTables.clear();
    for (int i = 0; i < employeeList.size(); i++)
    {
      workersTables.add(new WorkersTable(employeeList.get(i)));
    }
    isTaskSelected.set(true);
  }

  public boolean isIsTaskSelected() {
    return isTaskSelected.get();
  }

  public BooleanProperty isTaskSelectedProperty() {
    return isTaskSelected;
  }

  public void setIsTaskSelected(boolean isTaskSelected) {
    this.isTaskSelected.set(isTaskSelected);
  }
}
