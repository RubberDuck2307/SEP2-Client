package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import model.*;
import util.Validator;
import viewmodel.TaskView.TasksTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class EditTaskViewModel implements ViewModel
{
  private Model model;
  private ViewState viewState;
  private StringProperty nameOfTheProject;
  private StringProperty title;
  private StringProperty errorTitleMessage;
  private StringProperty errorTitleHours;
  private ObjectProperty<LocalDate> deadline;
  private StringProperty description;
  private ObjectProperty<String> priority;
  private ObjectProperty<String> status;
  private StringProperty estimatedHours;
  private StringProperty tags;
  private int estimatedHoursInt;
  private StringProperty errorPriorityMessage;
  private StringProperty errorDeadlineMessage;
  private Validator validator;



  public EditTaskViewModel(Model model, ViewState viewState)
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
    this.status = new SimpleObjectProperty<>();
    this.validator = new Validator();
    this.errorDeadlineMessage = new SimpleStringProperty();
    this.errorPriorityMessage = new SimpleStringProperty();
  }
  public void load()
  {
    Project project = viewState.getProject();
    nameOfTheProject.setValue(project.getName());
    Task task = viewState.getTask();
    status.setValue(task.getStatus());
    priority.setValue(task.getPriority());
    title.setValue(task.getName());
    description.setValue(task.getDescription());
    estimatedHours.setValue(task.getEstimatedTime() + "");
    errorTitleMessage.setValue("");
    errorTitleHours.setValue("");
  }
  public void setPriority(){
    Task task = viewState.getTask();
    priority.setValue(task.getPriority());
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
    Task task1 = viewState.getTask();
    boolean valid = true;

    if (Objects.equals(priority.getValue(), null))
    {
      priority.setValue(task1.getPriority());
    }
    if (Objects.equals(status.getValue(), null))
    {
      status.setValue(task1.getStatus());
    }
    try
    {
      validator.validateTitle(title.getValue());
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
    if (valid)
    {
      Task task2 = new Task(task1.getId(), title.getValue(), description.getValue(), deadline.getValue(), estimatedHoursInt, priority.getValue(), status.getValue(),
          project.getId(), LocalDate.now());
      System.out.println("Bobek: " + task2.toString());
      model.updateTask(task2);
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


  public StringProperty errorTitleMessageProperty()
  {
    return errorTitleMessage;
  }

  public String getEstimatedHours()
  {
    return estimatedHours.get();
  }

  public String getErrorPriorityMessage()
  {
    return errorPriorityMessage.get();
  }

  public StringProperty errorPriorityMessageProperty()
  {
    return errorPriorityMessage;
  }

  public String getErrorDeadlineMessage()
  {
    return errorDeadlineMessage.get();
  }

  public StringProperty errorDeadlineMessageProperty()
  {
    return errorDeadlineMessage;
  }

  public ObjectProperty<String> statusProperty()
  {
    return status;
  }

  public ObjectProperty<LocalDate> deadlineProperty()
  {
    return deadline;
  }


  public StringProperty descriptionProperty()
  {
    return description;
  }


  public ObjectProperty<String> priorityProperty()
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


  public StringProperty errorTitleHoursProperty()
  {
    return errorTitleHours;
  }
}
