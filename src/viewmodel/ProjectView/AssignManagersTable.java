package viewmodel.ProjectView;

import javafx.beans.property.*;
import model.Employee;

public class AssignManagersTable
{
  private IntegerProperty number;
  private StringProperty managerName;
  private BooleanProperty checkbox;

  public AssignManagersTable(Employee employee)
  {
    this.number = new SimpleIntegerProperty(employee.getWorkingNumber());
    this.managerName = new SimpleStringProperty(employee.getName());
    this.checkbox = new SimpleBooleanProperty();
  }

  public boolean isCheckbox()
  {
    return checkbox.get();
  }

  public BooleanProperty getCheckboxProperty()
  {
    return checkbox;
  }

  public void setCheckbox(boolean checkbox)
  {
    this.checkbox.set(checkbox);
  }

  @Override
  public String toString()
  {
    return "AssignManagersTable{" + "number=" + number + ", manager name=" + managerName + '}';
  }




}
