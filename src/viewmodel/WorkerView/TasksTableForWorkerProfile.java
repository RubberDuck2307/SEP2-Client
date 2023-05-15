package viewmodel.WorkerView;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import model.Project;
import model.Task;

import java.time.LocalDate;

public class TasksTableForWorkerProfile
{
  private StringProperty title;
  private StringProperty deadline;
  private StringProperty priority;
  private StringProperty status;
  private StringProperty projectName;

  private Long id;


  public TasksTableForWorkerProfile(Task task, Project project)
  {
    this.title = new SimpleStringProperty(task.getName());
    setDeadline(task.getDeadline());
    this.priority = new SimpleStringProperty(task.getPriority());
    this.status = new SimpleStringProperty(task.getStatus());
    this.projectName = new SimpleStringProperty(project.getName());
    this.id=task.getId();
  }

  public void setDeadline(LocalDate deadline)
  {
    if(deadline!=null)    this.deadline = new SimpleStringProperty(deadline.toString());
    else this.deadline=new SimpleStringProperty("");
  }

  public String getTitle()
  {
    return title.get();
  }

  public StringProperty titleProperty()
  {
    return title;
  }

  public String getDeadline()
  {
    return deadline.get();
  }

  public Long getId()
  {
    return id;
  }

  public StringProperty deadlineProperty()
  {
    return deadline;
  }

  public String getPriority()
  {
    return priority.get();
  }

  public String getProjectName()
  {
    return projectName.get();
  }

  public StringProperty projectNameProperty()
  {
    return projectName;
  }

  public StringProperty priorityProperty()
  {
    return priority;
  }

  public String getStatus()
  {
    return status.get();
  }

  public StringProperty statusProperty()
  {
    return status;
  }


  public ObservableValue<String> getTitleProperty()
  {
    return title;
  }
  public ObservableValue<String> getDeadlineProperty()
  {
    return deadline;
  }
  public ObservableValue<String> getPriorityProperty()
  {
    return priority;
  }
  public ObservableValue<String> getStatusProperty()
  {
    return status;
  }
}
