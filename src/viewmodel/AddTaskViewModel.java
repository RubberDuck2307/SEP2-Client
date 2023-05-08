package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import model.*;
import viewmodel.TaskView.TasksTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class AddTaskViewModel implements ViewModel
{
  private Model model;
  private ViewState viewState;
  private StringProperty nameOfTheProject;
  private StringProperty title;
  private StringProperty errorTitleMessage;
  private StringProperty errorPriorityMessage;
  private StringProperty errorTitleHours;
  private StringProperty errorDeadlineMessage;
  private ObjectProperty<LocalDate> deadline;
  private StringProperty description;
  private ObjectProperty<String> priority;
  private StringProperty estimatedHours;
  private StringProperty tags;
  private int estimatedHoursInt;
  private Validator validator;
  private EmployeeList workers;
  private ArrayList<Integer> assignedEmployeeIDs;


  public AddTaskViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.nameOfTheProject = new SimpleStringProperty();
    this.title = new SimpleStringProperty();
    this.errorTitleMessage = new SimpleStringProperty();
    LocalDate localDate = LocalDate.now();
    this.deadline = new SimpleObjectProperty<>(localDate);
    this.description = new SimpleStringProperty();
    this.priority = new SimpleObjectProperty<>();
    this.estimatedHours = new SimpleStringProperty();
    this.tags = new SimpleStringProperty();
    this.errorTitleHours = new SimpleStringProperty();
    this.errorDeadlineMessage = new SimpleStringProperty();
    this.errorPriorityMessage = new SimpleStringProperty();
    this.validator = new Validator();
    this.workers = new EmployeeList();
    this.assignedEmployeeIDs = new ArrayList<>();
  }
  public void load()
  {
    Project project = viewState.getProject();
    nameOfTheProject.setValue(project.getName());
    deadline.setValue(project.getDeadline());
    workers = model.getEmployeesAssignedToManager(4);
    assignedEmployeeIDs.clear();
    errorTitleHours.setValue(null);
    errorDeadlineMessage.setValue(null);
    errorTitleMessage.setValue(null);
    errorPriorityMessage.setValue(null);
    title.setValue("");
    priority.setValue(null);
    description.setValue("");
    estimatedHours.setValue(null);
  }
  public StringProperty getNameOfTheProject()
  {
    return nameOfTheProject;
  }
  public boolean add(){
    errorTitleHours.setValue(null);
    errorDeadlineMessage.setValue(null);
    errorTitleMessage.setValue(null);
    errorPriorityMessage.setValue(null);
    Project project = viewState.getProject();
    boolean valid = true;
    try
    {
      validator.validateTitle(title);
    }
    catch (Exception e)
    {
      valid = false;
      errorTitleMessage.setValue("Title can not be empty.");
    }
    try
    {
      validator.validateEstimatedTimer(getEstimatedHours());
    }
    catch (Exception e)
    {
      valid = false;
      errorTitleHours.setValue(e.getMessage());
    }
    if(deadline!=null){
      if (deadline.getValue().isAfter(project.getDeadline())){
        valid = false;
        errorDeadlineMessage.setValue("Deadline of the task can not be later than deadline of the project.");
      }
    }
    if (priority.getValue()==null){
      valid = false;
      errorPriorityMessage.setValue("The priority needs to be set.");
    }

    if (valid)
    {
      //TODO validate priority!!

      Task task2 = new Task(title.getValue(), description.getValue(), deadline.getValue(), estimatedHoursInt, priority.getValue(), "TO DO",
          project.getId(), LocalDate.now());
      System.out.println("Bobek: " + task2.toString());
      model.saveTask(task2);
      model.assignEmployeesToTask(assignedEmployeeIDs, viewState.getProject().getId());
    }
    return valid;
  }

  public StringProperty nameOfTheProjectProperty()
  {
    return nameOfTheProject;
  }

  public StringProperty titleProperty()
  {
    return title;
  }



  public StringProperty errorPriorityMessageProperty()
  {
    return errorPriorityMessage;
  }

  public StringProperty errorDeadlineMessageProperty()
  {
    return errorDeadlineMessage;
  }

  public StringProperty errorTitleMessageProperty()
  {
    return errorTitleMessage;
  }



  public ObjectProperty<LocalDate> deadlineProperty()
  {
    return deadline;
  }


  public StringProperty descriptionProperty()
  {
    return description;
  }


  public ObjectProperty priorityProperty()
  {
    return priority;
  }



  public StringProperty estimatedHoursProperty()
  {
    return estimatedHours;
  }

  public StringProperty tagsProperty()
  {
    return tags;
  }

  public String getEstimatedHours()
  {
    return estimatedHours.get();
  }

  public StringProperty errorTitleHoursProperty()
  {
    return errorTitleHours;
  }

  public void assignWorker(Employee employee){
    assignedEmployeeIDs.add(employee.getWorkingNumber());
  }
  public EmployeeList getWorkers() {
    return workers;
  }
}
