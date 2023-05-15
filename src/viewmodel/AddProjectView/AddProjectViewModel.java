package viewmodel.AddProjectView;

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
import viewmodel.ViewState;

import java.time.LocalDate;
import java.util.Objects;

public class AddProjectViewModel implements ViewModel
{
  private Validator validator;
  private ObjectProperty<Image> avatarPic;
  private ObjectProperty<Employee> employee;
  private StringProperty titleProperty;
  private StringProperty titleEProperty;
  private SimpleObjectProperty<LocalDate> deadlineProperty;
  private StringProperty deadlineEProperty;
  private StringProperty descriptionProperty;
  private ObservableList<AssignManagersTable> assignManagersObservableList;
  private AssignManagersTable assignManagersTable;
  private StringProperty errorProperty;
  private EmployeeList managers;
  private EmployeeList employeesOfProject;
  private StringProperty name;
  private StringProperty workingNumber;
  private ViewState viewState;
  private Model model;

  public AddProjectViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.employee = new SimpleObjectProperty<>();
    this.titleProperty = new SimpleStringProperty();
    this.descriptionProperty = new SimpleStringProperty();
    this.titleEProperty = new SimpleStringProperty();
    this.deadlineEProperty = new SimpleStringProperty();
    this.deadlineProperty = new SimpleObjectProperty<>();
    this.avatarPic = new SimpleObjectProperty<>();
    managers = new EmployeeList();
    this.validator = new Validator();
    employeesOfProject = new EmployeeList();
    this.name = new SimpleStringProperty();
    this.workingNumber = new SimpleStringProperty();
    assignManagersObservableList = FXCollections.observableArrayList();
  }

  public void load()
  {
    employee.setValue(model.getUser());
    setAvatarPicture();
    managers = model.getAllProjectManagers();
    name.setValue(this.model.getUser().getName());
    workingNumber.setValue(this.model.getUser().getWorkingNumber().toString());
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

  public Employee getEmployeeProperty()
  {
    return employee.get();
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

  public String getName()
  {
    return name.get();
  }

  public StringProperty nameProperty()
  {
    return name;
  }

  public String getWorkingNumber()
  {
    return workingNumber.get();
  }

  public StringProperty workingNumberProperty()
  {
    return workingNumber;
  }

  public boolean isWoman()
  {
    return Objects.equals(employee.getValue().getGender(), "F");
  }

  public void setAvatarPicture()
  {
    if (isWoman())
    {
      avatarPic.setValue(new Image("/icons/woman-avatar.png"));
    }
    else
    {
      avatarPic.setValue(new Image("/icons/man-avatar.png"));
    }
  }

  public ObjectProperty<Image> avatarPicProperty()
  {
    return avatarPic;
  }
}
