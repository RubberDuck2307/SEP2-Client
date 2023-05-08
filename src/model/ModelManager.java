package model;


import mediator.ClientInterface;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManager implements Model {

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

    @Override public Long saveTask(Task task)
    {
       return client.saveTask(task);
    }

    @Override public void updateTask(Task task)
    {
        client.updateTask(task);
    }
    
    @Override public Integer saveEmployee(Employee employee, String password)
    {
        return client.saveEmployee(employee,password);
    }
}
