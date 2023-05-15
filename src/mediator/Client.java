package mediator;

import model.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Client implements ClientInterface {

    private RemoteModel model;

    public Client() throws MalformedURLException, NotBoundException, RemoteException {
        model = (RemoteModel) Naming.lookup("rmi://localhost:1099/Case");
    }


    @Override
    public TaskList getAllTasksOfProject(Long id) {
        try {
            return model.getAllTasksOfProject(id);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void assignWorkerToTask(Integer workingNumber, Long taskID){
        try
        {
            model.assignWorkerToTask(workingNumber, taskID);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeWorkerFromTask(Integer workingNumber, Long taskID) {
        try {
            model.removeWorkerFromTask(workingNumber, taskID);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee login(UserProfile userProfile) {
        try {
            return model.login(userProfile);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public Task getTask(Long projectId){
        try
        {
            return model.getTask(projectId);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeList getEmployeesOfTask(Long taskId) {
        try {
            return model.getEmployeesOfTask(taskId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProjectList getAllProjectsByWorkingNumber(Integer workingNumber){
        try {
            return model.getAllProjectsByWorkingNumber(workingNumber);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProjectList getAllProjects(){
        try {
            return model.getAllProjects();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    public void changeTaskStatus(Long taskId, String status) {
        try
        {
            model.changeTaskStatus(taskId, status);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override public EmployeeList getEmployeesAssignedToManager(
        int managerNumber)
    {
        try
        {
            return model.getEmployeesAssignedToManager(managerNumber);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeList getAllEmployeesAssignedToProject(Long projectId) {
        try {
            return model.getAllEmployeesAssignedToProject(projectId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override public Integer saveEmployee(Employee employee, String password)
    {
        try
        {
            return model.saveEmployee(employee,password);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void assignEmployeesToTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID) {
        try {
            model.assignEmployeesToTask(employeeWorkingNumbers, TaskID);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeList getAllProjectManagers() {
        try {
            return model.getAllProjectManagers();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public EmployeeList getAllWorkers()
    {
        try
        {
            return model.getAllWorkers();
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override public void assignEmployeeToProject(Integer workingNumber,
        Long projectID)
    {
        try
        {
            model.assignEmployeeToProject(workingNumber, projectID);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override public void removeEmployeeFromProject(Integer workingNumber,
        Long projectID)
    {
        try
        {
            model.removeEmployeeFromProject(workingNumber, projectID);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override public void saveProject(Project project)
    {
        try
        {
            model.saveProject(project);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    @Override public void updateProject(Project project)
    {
        try
        {
            model.updateProject(project);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void unassignEmployeesFromTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID){
        try
        {
            model.unassignEmployeesFromTask(employeeWorkingNumbers, TaskID);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override public Long saveTask(Task task)
    {
        try
        {
           return model.saveTask(task);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override public void assignWorkerToManager(int managerNumber,
        int workerNumber)
    {
        try
        {
            model.assignWorkerToManager(managerNumber, workerNumber);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override public void removeWorkerFromManager(int managerNumber,
        int workerNumber)
    {
        try
        {
            model.removeWorkerFromManager(managerNumber, workerNumber);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    public String hello(){
        try {
            return model.hello();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public void updateTask(Task task)
    {
        try
        {
            model.updateTask(task);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override public EmployeeList getAllEmployees()
    {
        try
        {
            return model.getAllEmployees();
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override public Employee getEmployeeByWorkingNumber(int workingNumber)
    {
        try
        {
            return model.getEmployeeByWorkingNumber(workingNumber);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override public Project getProjectById(long projectId)
    {
        try
        {
            return model.getProjectById(projectId);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override public TaskList getAllTasksByUserId(Integer workingNumber)
    {
        try
        {
            return model.getAllTasksByUserId(workingNumber);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override public EmployeeList getAllWorkersManagersByWorkerWorkingNumber(
        Integer workingNumber)
    {
        try
        {
            return model.getAllWorkersManagersByWorkerWorkingNumber(workingNumber);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }
}
