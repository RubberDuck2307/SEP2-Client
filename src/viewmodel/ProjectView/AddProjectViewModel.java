package viewmodel.ProjectView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import model.Employee;
import model.EmployeeList;
import model.Model;
import model.Project;
import util.Validator;
import viewmodel.ViewModel;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Objects;

public class AddProjectViewModel extends ViewModelWithNavigationMenu
{
  private Validator validator;
  private StringProperty titleProperty;
  private StringProperty titleEProperty;
  private SimpleObjectProperty<LocalDate> deadlineProperty;
  private StringProperty deadlineEProperty;
  private StringProperty descriptionProperty;
  private EmployeeList managers;
  private EmployeeList employeesOfProject;

  public AddProjectViewModel(Model model)
  {
    super(model);

    this.titleProperty = new SimpleStringProperty();
    this.descriptionProperty = new SimpleStringProperty();
    this.titleEProperty = new SimpleStringProperty();
    this.deadlineEProperty = new SimpleStringProperty();
    this.deadlineProperty = new SimpleObjectProperty<>();
    managers = new EmployeeList();
    this.validator = new Validator();
    employeesOfProject = new EmployeeList();
  }

  public void load()
  {
    super.load();
    managers = model.getAllProjectManagers();

  }

  public void assignEmployee(Employee employee)
  {
    if (!employeesOfProject.containsByWorkingNumber(
        employee.getWorkingNumber()))
    {
      employeesOfProject.addEmployee(employee);
    }
    else
    {
      employeesOfProject.removeByWorkingNumber(employee.getWorkingNumber());
    }
  }

  public void reset()
  {
    super.reset();
    titleProperty.setValue("");
    descriptionProperty.setValue("");
    deadlineProperty.setValue(null);

    load();
  }

  public boolean addProject()
  {
    Boolean valid = true;
    try
    {
      validator.validateTitle(titleProperty.getValue());
    }
    catch (Exception e)
    {
      valid = false;
      titleEProperty.setValue(e.getMessage());
    }
    try
    {
      validator.validateDeadline(deadlineProperty.get());
    }
    catch (Exception e)
    {
      valid = false;
      deadlineEProperty.setValue(e.getMessage());
    }
    if (valid)
    {
      Project project = new Project(titleProperty.get(), descriptionProperty.get(), deadlineProperty.get());
      Long ID=model.saveProject(project);
      for (int i = 0; i < employeesOfProject.size(); i++)
      {
        model.assignEmployeeToProject(employeesOfProject.get(i).getWorkingNumber(), ID);
      }
    }
    return valid;
  }

  public StringProperty getTitleProperty()
  {
    return titleProperty;
  }

  public StringProperty getDescriptionProperty()
  {
    return descriptionProperty;
  }


  public StringProperty getTitleErrorProperty()
  {
    return titleEProperty;
  }

  public StringProperty getDeadlineErrorProperty()
  {
    return deadlineEProperty;
  }

  public SimpleObjectProperty<LocalDate> getDeadlineProperty()
  {
    return deadlineProperty;
  }

  public EmployeeList getManagers()
  {
    return managers;
  }


  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    super.propertyChange(evt);
  }

}
