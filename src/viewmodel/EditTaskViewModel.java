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

import java.time.LocalDate;
import java.util.ArrayList;

public class EditTaskViewModel implements ViewModel
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


  public EditTaskViewModel(Model model, ViewState viewState)
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
    Task task = viewState.getTask();
    //title.setValue(task.getName());
  }
  public StringProperty getNameOfTheProject()
  {
    return nameOfTheProject;
  }
  public void add(Task task){
    // TODO finish this:
    Project project = viewState.getProject();
    if(task.getDeadline()==null){

      task.setDeadline(project.getDeadline());
    }
    //TODO edit later
    /*
    if(task.getWorkers().size()==0){
      ArrayList<Employee> employees = new ArrayList<>();

      Employee employee = new Employee(2,"Anna", LocalDate.now(),"123456789", "MALE", EmployeeRole.HR,"aaa" );
      employees.add(employee);
      task.setWorkers(employees);
    }*/
    Task task2 = new Task(project.getId(),task.getName(),task.getDescription(),task.getDeadline(),task.getEstimatedTime(),
        task.getPriority(), task.getStatus(), task.getProjectId(),task.getStartingDate(), task.getWorkers());
    System.out.println("Bobek: " + task2.toString());
    //model.saveTask(task);
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
