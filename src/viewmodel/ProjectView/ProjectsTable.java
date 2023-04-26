package viewmodel.ProjectView;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.time.LocalDate;

public class ProjectsTable
{
  private StringProperty title;
  private ObjectProperty<LocalDate> deadline;
  private StringProperty manager;

  public ProjectsTable(String title, LocalDate deadline, String manager)
  {
    this.title = new SimpleStringProperty(title);
    this.deadline = new SimpleObjectProperty<>();
    this.manager = new SimpleStringProperty(manager);
  }

  public String getTitle()
  {
    return title.get();
  }

  public StringProperty titleProperty()
  {
    return title;
  }

  public LocalDate getDeadline()
  {
    return deadline.get();
  }

  public ObjectProperty<LocalDate> deadlineProperty()
  {
    return deadline;
  }

  public String getManager()
  {
    return manager.get();
  }

  public StringProperty managerProperty()
  {
    return manager;
  }

  public ObservableValue<String> getTitleValue()

  {
    return title;
  }public ObservableValue<LocalDate> getDeadlineValue()

  {
    return deadline;
  }public ObservableValue<String> getManagerValue()

  {
    return manager;
  }
}
