package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import model.*;
import viewmodel.TaskView.TasksTable;

public class AddTaskViewModel implements ViewModel
{
  private Model model;
  private ViewState viewState;
  private StringProperty nameOfTheProject;
  private StringProperty title;
  private StringProperty errorTitleMessage;
  private StringProperty errorTitleHours;
  private StringProperty deadline;
  private StringProperty description;
  private StringProperty priority;
  private IntegerProperty estimatedHours;
  private StringProperty tags;


  public AddTaskViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.nameOfTheProject = new SimpleStringProperty();
    this.title = new SimpleStringProperty();
    this.errorTitleMessage = new SimpleStringProperty();
    this.deadline = new SimpleStringProperty();
    this.description = new SimpleStringProperty();
    this.priority = new SimpleStringProperty();
    this.estimatedHours = new SimpleIntegerProperty();
    this.tags = new SimpleStringProperty();
    this.errorTitleHours = new SimpleStringProperty();

    load();
  }
  public void load()
  {
    Project project = viewState.getProject();
    nameOfTheProject.setValue(project.getName());
    deadline.setValue(project.getDeadline().toString());
  }
  public StringProperty getNameOfTheProject()
  {
    return nameOfTheProject;
  }
  public void add(Task task){
    // TODO finish this: model.addTask(task);
    if(task.getDeadline()==null){
      Project project = viewState.getProject();
      task.setDeadline(project.getDeadline());
      task.setDescription(project.getDescription());
    }
    System.out.println("Bobek: " + task.toString());
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



  public StringProperty deadlineProperty()
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



  public IntegerProperty estimatedHoursProperty()
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
