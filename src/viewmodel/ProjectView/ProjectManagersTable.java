package viewmodel.ProjectView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import model.Employee;

public class ProjectManagersTable
{
  private StringProperty name;
  private StringProperty role;

  public ProjectManagersTable(Employee projectManager){
    this.name=new SimpleStringProperty(projectManager.getName());
    this.role = new SimpleStringProperty(projectManager.getRoleString());
  }

  public String getName()
  {
    return name.get();
  }

  public void setName(String name)
  {
    this.name.set(name);
  }

  public ObservableValue<String> getNameValue()

  {
    return name;
  }public ObservableValue<String> getRoleValue()

  {
    return role;
  }
}
