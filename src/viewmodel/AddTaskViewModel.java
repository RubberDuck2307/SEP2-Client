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

public class AddTaskViewModel implements ViewModel
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
  private StringProperty estimatedHours;
  private StringProperty tags;
  private int estimatedHoursInt;
  private Validator validator;


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
    this.priority = new SimpleStringProperty();
    this.estimatedHours = new SimpleStringProperty();
    this.tags = new SimpleStringProperty();
    this.errorTitleHours = new SimpleStringProperty();
    this.validator = new Validator();



  }
  public void load()
  {
    Project project = viewState.getProject();
    nameOfTheProject.setValue(project.getName());
    deadline.setValue(project.getDeadline());

  }
  public StringProperty getNameOfTheProject()
  {
    return nameOfTheProject;
  }
  public boolean add(){
    Project project = viewState.getProject();
    boolean valid = true;
    try
    {
      validator.validateTitle(title);
    }
    catch (Exception e)
    {
      valid = false;
      errorTitleMessage.setValue("Title can not be empty");
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
    if (valid)
    {
      Task task2 = new Task(title.getValue(), description.getValue(), deadline.getValue(), estimatedHoursInt, priority.getValue(), "TO DO",
          project.getId(), LocalDate.now());
      System.out.println("Bobek: " + task2.toString());
      model.saveTask(task2);
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

  public String getEstimatedHours()
  {
    return estimatedHours.get();
  }

  public StringProperty errorTitleHoursProperty()
  {
    return errorTitleHours;
  }
}
