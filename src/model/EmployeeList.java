package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class EmployeeList implements Serializable
{
  private ArrayList<Employee> employeesList;
  public EmployeeList(){
    employeesList= new ArrayList<>();
  }

  public EmployeeList(ArrayList<Employee> employeesList) {
    this.employeesList = employeesList;
  }

  public Employee getEmployeeById(int id){
    for (Employee employee: employeesList){
      if (Objects.equals(employee.getWorkingNumber(), id)){
        return employee;
      }
    }
    throw new RuntimeException("No task with such id found");
  }
  public void addEmployee(Employee employee){
    employeesList.add(employee);
  }

  public Employee getEmployee(int index){
    return employeesList.get(index);
  }

  public Employee get(int index){
    return this.employeesList.get(index);
  }

  public int size(){
    return employeesList.size();
  }

  public void setEmployeesList(ArrayList<Employee> employeesList) {
    this.employeesList = employeesList;
  }

  public boolean containsByWorkingNumber(Integer workingNumber){
    for (Employee employee: employeesList){
      if (Objects.equals(employee.getWorkingNumber(), workingNumber)){
        return true;
      }
    }
    return false;
  }

  public void removeByWorkingNumber(Integer workingNumber){
    for (Employee employeeSaved: employeesList){
      if (Objects.equals(employeeSaved.getWorkingNumber(), workingNumber)){
        employeesList.remove(employeeSaved);
        return;
      }
    }
  }

  @Override
  public String toString() {
    return "{" +  employeesList +
            '}';
  }
}
