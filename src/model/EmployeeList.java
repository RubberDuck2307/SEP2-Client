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

  public int size(){
    return employeesList.size();
  }

  @Override
  public String toString() {
    return "{" +  employeesList +
        '}';
  }
}
