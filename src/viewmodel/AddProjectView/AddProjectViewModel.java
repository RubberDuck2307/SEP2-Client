package viewmodel.AddProjectView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeList;
import model.Model;
import model.Project;
import util.Validator;
import viewmodel.ViewModel;
import viewmodel.ViewState;
import java.time.LocalDate;

public class AddProjectViewModel implements ViewModel
{
  private Validator validator;
  private StringProperty titleProperty;
  private StringProperty titleEProperty;
  private SimpleObjectProperty<LocalDate> deadlineProperty;
  private StringProperty deadlineEProperty;
  private StringProperty descriptionProperty;
  private ObservableList<AssignManagersTable> assignManagersObservableList;
  private AssignManagersTable assignManagersTable;
  private EmployeeList employeesList;
  private ObservableList<Employee> employees;
  private ViewState viewState;
  private Model model;

  public AddProjectViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.titleProperty = new SimpleStringProperty();
    this.descriptionProperty = new SimpleStringProperty();
    this.titleEProperty = new SimpleStringProperty();
    this.deadlineEProperty = new SimpleStringProperty();
    this.deadlineProperty = new SimpleObjectProperty<>();
    this.validator=new Validator();
    employeesList = new EmployeeList();
    assignManagersObservableList = FXCollections.observableArrayList();
    employees = FXCollections.observableArrayList();
  }
  public void reset()
  {
    titleProperty.setValue("");
    descriptionProperty.setValue("");
    deadlineProperty.setValue(null);
  }


  public boolean addProject(){
    Boolean valid=true;
    try
    {
      validator.validateTitle(titleProperty.getValue());
      //firstNameValue.setValue(true);
      //titleEProperty.setValue("✓");
    }
    catch (Exception e)
    {
      //titleEProperty.setValue(false);
      valid = false;
      titleEProperty.setValue(e.getMessage());
    }
    try
    {
      validator.validateDeadline(deadlineProperty.get());
      //firstNameValue.setValue(true);
      //deadlineEProperty.setValue("✓");
    }
    catch (Exception e)
    {
      //titleEProperty.setValue(false);
      valid = false;
      deadlineEProperty.setValue(e.getMessage());
    }
    if(valid) model.saveProject(new Project(titleProperty.get(), descriptionProperty.get(), deadlineProperty.get()));
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

  public ObservableList<AssignManagersTable> getAssignManagersObservableList()
  {
    return assignManagersObservableList;
  }

  public ObservableList<Employee> getEmployees()
  {
    return employees;
  }

  public void setAssignManagersObservableList(
      ObservableList<AssignManagersTable> assignManagersObservableList)
  {
    this.assignManagersObservableList = assignManagersObservableList;
  }

  public void setEmployees(ObservableList<Employee> employees)
  {
    this.employees = employees;
  }

}
