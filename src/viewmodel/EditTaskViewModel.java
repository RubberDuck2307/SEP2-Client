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
  private StringProperty priority;
  private StringProperty status;
  private StringProperty estimatedHours;
  private StringProperty tags;
  private int estimatedHoursInt;



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
    this.priority = new SimpleStringProperty();
    this.estimatedHours = new SimpleStringProperty();
    this.tags = new SimpleStringProperty();
    this.errorTitleHours = new SimpleStringProperty();
    this.status = new SimpleStringProperty();
  }
  public void load()
  {
    Project project = viewState.getProject();
    nameOfTheProject.setValue(project.getName());
    Task task = viewState.getTask();
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
  public void add(){
    Project project = viewState.getProject();
    Task task1 = viewState.getTask();
    boolean valid = true;
    if (title.getValue().trim().isEmpty() || title.getValue().trim().length() <= 1)
    {
      valid = false;
      errorTitleMessage.setValue("Name cannot be empty!");
    }

    if (deadline.toString().equals(""))
    {
      deadline.setValue(task1.getDeadline());
    }

    if (Objects.equals(priority.getValue(), null))
    {
      priority.setValue(task1.getPriority());
    }
    if (Objects.equals(status.getValue(), null))
    {
      status.setValue(task1.getStatus());
    }
    if (Objects.equals(estimatedHours.getValue().trim(), ""))
    {
      estimatedHoursInt = 0;
    }
    if (!Objects.equals(estimatedHours.getValue(), ""))
    {
      try{
        estimatedHoursInt = Integer.parseInt(estimatedHours.getValue());
      }
      catch (NumberFormatException ex){
        valid = false;
        errorTitleHours.setValue("Please insert only numbers");
      }
    }

    if (valid)
    {
      //todo display date and fix choice selectors and validation
      Task task2 = new Task(task1.getId(), title.getValue(), description.getValue(), deadline.getValue(), estimatedHoursInt, priority.getValue(), status.getValue(),
          project.getId(), LocalDate.now());
      System.out.println("Bobek: " + task2.toString());
      model.updateTask(task2);
    }

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



  public ObjectProperty<LocalDate> deadlineProperty()
  {
    return deadline;
  }


  public StringProperty descriptionProperty()
  {
    return description;
  }


  public StringProperty priorityProperty()
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
