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
  private TaskList taskList;
  private EmployeeList employeeList;
  private StringProperty error;
  private StringProperty projectName;
  private StringProperty taskName;
  private StringProperty taskDescription;
  private ObservableList<Task> tasks;
  private ObservableList<TasksTable> tasksTables;
  private ObservableList<CommentsTable> commentsTables;
  //workers
  private ObservableList<WorkersTable> workersTables;
  private StringProperty name;
  private IntegerProperty number;
  private StringProperty position;
  private ViewState viewState;

  public TasksViewModel(Model model, ViewState viewState)
  {
    this.error = new SimpleStringProperty();
    this.model = model;
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
    load();
  }

  public void load()
  {
    Project project = viewState.getProject();
    taskList = model.getAllTasksOfProject(project.getId());
    taskName.setValue("Description");
    projectName.setValue(project.getName());
    taskDescription.setValue("Select a task to see the description and comments");

    tasksTables.clear();
    for (int i = 0; i < taskList.size(); i++)
    {
      tasksTables.add(new TasksTable(taskList.getTask(i)));
    }

  }

  public ObservableList<TasksTable> getAll()
  {
    return tasksTables;
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

  public void chooseTask(Long id)
  {
    Task task=taskList.getTaskById(id);
    taskName.setValue(task.getName());
    taskDescription.setValue(task.getDescription());
    ArrayList<Employee> employees = taskList.getTaskById(id).getWorkers();
    workersTables.clear();
    for (int i = 0; i < employees.size(); i++)
    {
      workersTables.add(new WorkersTable(employees.get(i)));
    }
  }
}
