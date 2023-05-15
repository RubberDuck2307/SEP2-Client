package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Employee;
import model.Model;
import model.UserProfile;

public class LoginViewModel implements ViewModel
{

  private StringProperty passwordProperty;
  private StringProperty workingNumberProperty;
  private StringProperty errorProperty;
  private ObjectProperty<Employee> loggedEmployee;
  private Model model;

  public LoginViewModel(Model model)
  {
    this.passwordProperty = new SimpleStringProperty("");
    this.workingNumberProperty = new SimpleStringProperty("");
    this.errorProperty = new SimpleStringProperty("");
    this.loggedEmployee = new SimpleObjectProperty<Employee>();
    this.model = model;
  }

  public void reset()
  {
    this.passwordProperty.setValue("");
    this.workingNumberProperty.setValue("");
  }

  public boolean login()
  {
    Employee employee;
    UserProfile userProfile;
    try
    {
      userProfile = new UserProfile(
          Integer.parseInt(workingNumberProperty.get()),
          passwordProperty.get());
      errorProperty.set("");
    }
    catch (Exception e)
    {
      errorProperty.set("Invalid input");
      return false;
    }

    try
    {
      employee = model.login(userProfile);
      errorProperty.set("");
    }
    catch (Exception e)
    {
      e.printStackTrace();
      errorProperty.set("Server is not working");
      return false;
    }

    if (employee == null)
    {
      errorProperty.set("Wrong working number or password");
      return false;
    }
    else
    {
      errorProperty.set("");
      model.setUser(employee);
      loggedEmployee.setValue(model.getUser());
      return true;
    }
  }

  public String getPasswordProperty()
  {
    return passwordProperty.get();
  }

  public StringProperty passwordPropertyProperty()
  {
    return passwordProperty;
  }

  public String getWorkingNumberProperty()
  {
    return workingNumberProperty.get();
  }

  public StringProperty workingNumberPropertyProperty()
  {
    return workingNumberProperty;
  }

  public String getErrorProperty()
  {
    return errorProperty.get();
  }

  public StringProperty errorPropertyProperty()
  {
    return errorProperty;
  }

  public Employee getEmployeeProperty()
  {
    return loggedEmployee.get();
  }

}
