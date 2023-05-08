package viewmodel.AddProjectView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeList;
import model.Model;
import model.Project;
import viewmodel.ViewModel;
import viewmodel.ViewState;
import java.time.LocalDate;

public class AddProjectViewModel implements ViewModel
{
  private StringProperty titleProperty;
  private SimpleObjectProperty<LocalDate> deadlineProperty;
  private StringProperty descriptionProperty;
  private StringProperty errorProperty;
  private EmployeeList managers;
  private EmployeeList assignedManagers;

  private ViewState viewState;
  private Model model;

  public AddProjectViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.titleProperty = new SimpleStringProperty();
    this.descriptionProperty = new SimpleStringProperty();
    this.errorProperty = new SimpleStringProperty();
    this.deadlineProperty = new SimpleObjectProperty<>();
    managers = new EmployeeList();
  }

    public void load()
    {
      managers = model.getAllProjectManagers();
    }
  public void createButtonPressed(){
    model.saveProject(new Project(titleProperty.get(), descriptionProperty.get(), deadlineProperty.get()));

  }

  public StringProperty getTitleProperty()
  {
    return titleProperty;
  }

  public StringProperty getDescriptionProperty()
  {
    return descriptionProperty;
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  public SimpleObjectProperty<LocalDate> getDeadlineProperty()
  {
    return deadlineProperty;
  }

  public EmployeeList getManagers() {
    return managers;
  }
}
