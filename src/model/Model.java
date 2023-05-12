package model;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Model{

    TaskList getAllTasksOfProject(Long id) ;
    ProjectList getAllProjects();

    EmployeeList getAllWorkers();

    ProjectList getAllProjectsByWorkingNumber(Integer workingNumber);

    EmployeeList getEmployeesAssignedToManager(int managerNumber);
    void removeWorkerFromTask(Integer workingNumber, Long taskID);
    Employee login(UserProfile userProfile);
    void assignWorkerToTask(Integer workingNumber, Long taskID);
    EmployeeList getAllEmployeesAssignedToProject(Long projectId);
    void assignEmployeesToTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID);

    void assignEmployeeToProject(Integer workingNumber, Long projectID);
    void removeEmployeeFromProject(Integer workingNumber, Long projectID);
    EmployeeList getAllProjectManagers();
    void saveProject(Project project);
    EmployeeList getEmployeesOfTask(Long taskId);
    Long saveTask(Task task);
    Task getTask(Long projectId);

    void assignWorkerToManager(int managerNumber, int workerNumber);
    void removeWorkerFromManager(int managerNumber, int workerNumber);

    Employee getUser();
    void unassignEmployeesFromTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID);
    Integer saveEmployee(Employee employee, String password);
    void updateTask(Task task);
    EmployeeList getAllEmployees();
    void setUser(Employee user);
    Employee getEmployeeByWorkingNumber(int workingNumber);
}
