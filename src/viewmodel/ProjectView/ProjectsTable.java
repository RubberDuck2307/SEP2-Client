package viewmodel.ProjectView;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import model.Project;

import java.time.LocalDate;

public class ProjectsTable
{
  private StringProperty title;
  private ObjectProperty<String> deadline;


  private Long id;
  private Button button;

  public ProjectsTable(Project project){
    this.title = new SimpleStringProperty(project.getName());
    this.deadline = new SimpleObjectProperty<>();
    setDeadline(project.getDeadline());
    //this.manager = new SimpleStringProperty(project.getProjectManager().get(0).getName());
    this.id = project.getId();

    this.button=new Button(" ");

    openTasks();

    button.setId("showTasks");

  }
  public Button getButton()
  {
    return button;
  }
  public void openTasks(){
    button.setOnAction(e -> {
      System.out.println("hhhhhhh");
    });
  }

  public void setButton(Button button)
  {
    this.button = button;
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

  public ObservableValue<String> getTitleValue()

  {
    return title;
  }

  @Override
  public String toString() {
    return "ProjectsTable{" +
            "title=" + title +
            ", deadline=" + deadline +
            '}';
  }
}
