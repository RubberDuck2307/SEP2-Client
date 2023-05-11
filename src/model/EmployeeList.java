package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class representing a list of employees using Employee objects
 */

public class EmployeeList implements Serializable
{
  private ArrayList<Employee> employeesList;

  /**
   * The zero-argument constructor sets employeesList to new empty ArrayList
   */
  public EmployeeList(){
    employeesList= new ArrayList<>();
  }

  /**
   * The one-argument constructor sets the attribute employeesList to a employeesList from the given ArrayList
   * @param employeesList
   */
  public EmployeeList(ArrayList<Employee> employeesList) {
    this.employeesList = employeesList;
  }

  /**
   *
   * @param workingNumber
   * @return employee with the given workingNumber
   * @throws RuntimeException if there is no employee with such workingNumber in the list
   */
  public Employee getEmployeeByWorkingNumber(int workingNumber){
    for (Employee employee: employeesList){
      if (Objects.equals(employee.getWorkingNumber(), workingNumber)){
        return employee;
      }
    }
    throw new RuntimeException("No task with such workingNumber found");
  }

  /**
   * Adds an employee into the ArrayList employeesList.
   * @param employee
   */
  public void addEmployee(Employee employee){
    employeesList.add(employee);
  }



  /**
   *
   * @param index
   * @return employee with the given index
   */

  public Employee get(int index){
    return this.employeesList.get(index);
  }

  /**
   *
   * @return the size of employeeList
   */
  public int size(){
    return employeesList.size();
  }

  /**
   * sets the attribute employeesList to the given list
   * @param employeesList
   */
  public void setEmployeesList(ArrayList<Employee> employeesList) {
    this.employeesList = employeesList;
  }

  /**
   *
   * @param workingNumber
   * @return true if there is an employee with the given workingNumber in the list, false otherwise
   */

  public boolean containsByWorkingNumber(Integer workingNumber){
    for (Employee employee: employeesList){
      if (Objects.equals(employee.getWorkingNumber(), workingNumber)){
        return true;
      }
    }
    return false;
  }

  /**
   * Removes the employee with the given workingNumber from the list
   * @param workingNumber
   */
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
