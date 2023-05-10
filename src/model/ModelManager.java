package model;


import mediator.ClientInterface;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManager implements Model {

    private Employee user;
    private ClientInterface client;

    public ModelManager(ClientInterface client) {
        this.client = client;
    }

    @Override
    public TaskList getAllTasksOfProject(Long id) {
        return client.getAllTasksOfProject(id);
    }

    @Override
    public ProjectList getAllProjectsByWorkingNumber(Integer workingNumber) {
        return client.getAllProjectsByWorkingNumber(workingNumber);
    }

    @Override public EmployeeList getEmployeesAssignedToManager(
        int managerNumber)
    {
        return client.getEmployeesAssignedToManager(managerNumber);
    }

    @Override
    public void removeWorkerFromTask(Integer workingNumber, Long taskID) {
        client.removeWorkerFromTask(workingNumber, taskID);
    }

    @Override
    public Employee login(UserProfile userProfile) {
        Employee employee = client.login(userProfile);
        return employee;
    }

    @Override public void assignWorkerToTask(Integer workingNumber,
        Long taskID)
    {
        client.assignWorkerToTask(workingNumber, taskID);
    }

    @Override
    public EmployeeList getAllEmployeesAssignedToProject(Long projectId) {
        return client.getAllEmployeesAssignedToProject(projectId);
    }

    @Override
    public void assignEmployeesToTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID) {
        client.assignEmployeesToTask(employeeWorkingNumbers, TaskID);
    }

    @Override public void assignEmployeeToProject(Integer workingNumber,
        Long projectID)
    {
        client.assignEmployeeToProject(workingNumber, projectID);
    }

    @Override public void removeEmployeeFromProject(Integer workingNumber,
        Long projectID)
    {
        client.removeEmployeeFromProject(workingNumber, projectID);
    }

    @Override public EmployeeList getAllProjectManagers()
    {
        return client.getAllProjectManagers();
    }

    @Override public void saveProject(Project project)
    {
        client.saveProject(project);
    }

    @Override
    public EmployeeList getEmployeesOfTask(Long taskId) {
        return client.getEmployeesOfTask(taskId);
    }

    public void unassignEmployeesFromTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID){
        client.unassignEmployeesFromTask(employeeWorkingNumbers, TaskID);
    }
    @Override public Long saveTask(Task task)
    {
       return client.saveTask(task);
    }

    @Override public void updateTask(Task task)
    {
        client.updateTask(task);
    }

    @Override public EmployeeList getAllEmployees()
    {
        return client.getAllEmployees();
    }

    @Override public Integer saveEmployee(Employee employee, String password)
    {
        return client.saveEmployee(employee,password);
    }

    public void setUser(Employee user) {
        this.user = user;
    }

    public Employee getUser() {
        return user;
    }
}
