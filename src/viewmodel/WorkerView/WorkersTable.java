package viewmodel.WorkerView;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import model.Employee;

public class WorkersTable
{
  private IntegerProperty number;
  private StringProperty name;
  private StringProperty email;
  private StringProperty role;
  public WorkersTable(Employee employee){
    this.number = new SimpleIntegerProperty(employee.getWorkingNumber());
    this.name = new SimpleStringProperty(employee.getName());
    this.email = new SimpleStringProperty(employee.getEmail());
    this.role = new SimpleStringProperty(employee.getRole().toString());
  }

  public int getNumber()
  {
    return number.get();
  }

  public IntegerProperty numberProperty()
  {
    return number;
  }

  public String getName()
  {
    return name.get();
  }

  public StringProperty nameProperty()
  {
    return name;
  }

  public String getRole()
  {
    return role.get();
  }

  public StringProperty roleProperty()
  {
    return role;
  }

  public ObservableValue<String> getNumberProperty()
  {
    return number.asString();
  }
  public ObservableValue<String> getRoleProperty()
  {
    return role;
  }

  public ObservableValue<String> getNameProperty()
  {
    return name;
  }public ObservableValue<String> getEmailProperty()
  {
    return email;
  }

  public String getEmail()
  {
    return email.get();
  }
}
