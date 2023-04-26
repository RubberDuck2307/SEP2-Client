package viewmodel.ProjectView;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import model.Project;

import java.time.LocalDate;

public class ProjectsTable
{
  private StringProperty title;
  private ObjectProperty<String> deadline;
  private StringProperty manager;

  private Long id;

  public ProjectsTable(String title, LocalDate deadline, String manager)
  {
    this.title = new SimpleStringProperty(title);
    this.deadline = new SimpleObjectProperty<>();
    setDeadline(deadline);
    this.manager = new SimpleStringProperty(manager);
  }

  public ProjectsTable(Project project){
    this.title = new SimpleStringProperty(project.getName());
    this.deadline = new SimpleObjectProperty<>();
    setDeadline(project.getDeadline());
    this.manager = new SimpleStringProperty(project.getProjectManager().get(0).getName());
    this.id = project.getId();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle()
  {
    return title.get();
  }

  public StringProperty titleProperty()
  {
    return title;
  }

  public String getDeadline() {
    return deadline.get();
  }

  public ObjectProperty<String> deadlineProperty() {
    return deadline;
  }

  public void setDeadline(LocalDate deadline) {
    this.deadline.set(deadline.toString());
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
  }

  public ObservableValue<String> getManagerValue()

  {
    return manager;
  }

  @Override
  public String toString() {
    return "ProjectsTable{" +
            "title=" + title +
            ", deadline=" + deadline +
            ", manager=" + manager +
            '}';
  }
}
