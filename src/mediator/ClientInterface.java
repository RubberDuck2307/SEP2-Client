package mediator;

import model.*;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ClientInterface {

    Long saveTag(Tag tag);
    TagList getAllTags();
    void logOut();
    boolean addForgetPasswordNotification(Integer workingNumber);
    TagList getTagsOfTask(Long taskId);
    void addTagToTask(Long taskId, Long tagId);
    void removeTagFromTask(Long taskId, Long tagId);
    Tag getTag(Long tagId);
    void deleteTag(Long id);
    void addNotificationListener(PropertyChangeListener listener);
    TaskList getAllTasksOfProject(Long id) ;
    ProjectList getAllProjects();
    ProjectList getAllProjectsByWorkingNumber(Integer workingNumber);
    void assignWorkerToTask(Integer workingNumber, Long taskID);
    EmployeeList getEmployeesAssignedToManager(int managerNumber);
    void removeWorkerFromTask(Integer workingNumber, Long taskID);
    EmployeeList getAllEmployeesAssignedToProject(Long projectId);
    void assignEmployeesToTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID);
    EmployeeList getAllProjectManagers();
    EmployeeList getAllWorkers();

    void changeTaskStatus(Long taskId, String status);

    void assignEmployeeToProject(Integer workingNumber, Long projectID);
    void removeEmployeeFromProject(Integer workingNumber, Long projectID);
    Long saveProject(Project project);
    void updateProject(Project project);
    void unassignEmployeesFromTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID);
    void dismissEmployeesFromProject(ArrayList<Integer> employeeWorkingNumbers, Long projectID);
    void assignEmployeesToProject(ArrayList<Integer> addedEmployees, Long id);
    
    Long saveTask(Task task);
    void assignWorkerToManager(int managerNumber, int workerNumber);
    
    void removeWorkerFromManager(int managerNumber, int workerNumber);
    
    Integer saveEmployee(Employee employee, String password);
    
    Task getTask(Long projectId);
    
    Employee login(UserProfile userProfile);
    EmployeeList getEmployeesOfTask(Long taskId);
    String hello();
    void updateTask(Task task);
    EmployeeList getAllEmployees();
    Employee getEmployeeByWorkingNumber(int workingNumber);
    Project getProjectById(long projectId);
    TaskList getAllTasksByUserId(Integer workingNumber);
    
  EmployeeList getAllWorkersManagersByWorkerWorkingNumber(Integer workingNumber);
    void updateEmployee(Employee employee);
    void changePassword(Employee employee, String password);
}
