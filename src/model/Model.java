package model;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Model {

    IdObjectList<ForgottenPasswordNotification> getForgottenPasswordNotification();
    IdObjectList<AssignedToTaskNotification> getAssignedToTaskNotification(Integer workingNumber);
    IdObjectList<AssignedToProjectNotification> getAssignedToProjectNotification(Integer workingNumber);
    UserAccess getUserAccess();
    Long saveTag(Tag tag);

    TagList getAllTags();

    TagList getTagsOfTask(Long taskId);

    void addTagToTask(Long taskId, Long tagId);

    void removeTagFromTask(Long taskId, Long tagId);

    Tag getTag(Long tagId);

    void deleteTag(Long id);

    void logOut();

    boolean addForgetPasswordNotification(Integer workingNumber);

    TaskList getAllTasksOfProject(Long id);

    ProjectList getAllProjects();

    EmployeeList getAllWorkers();

    void changeTaskStatus(Long taskId, String status);


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

    Long saveProject(Project project);
    
    void deleteProjectById(Long id);

    void updateProject(Project project);

    EmployeeList getEmployeesOfTask(Long taskId);
    
    Long saveTask(Task task);
    
    void deleteTaskById(Long id);
    
    Task getTask(Long projectId);
    
    void assignWorkerToManager(int managerNumber, int workerNumber);
    
    void removeWorkerFromManager(int managerNumber, int workerNumber);
    
    Employee getUser();
    
    void unassignEmployeesFromTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID);
    
    Integer saveEmployee(Employee employee, String password);
    
    void deleteEmployeeByWorkingNumber(Integer workingNumber);
    
    void updateTask(Task task);
    
    EmployeeList getAllEmployees();
    
    void setUser(Employee user);
    
    Employee getEmployeeByWorkingNumber(int workingNumber);
    
    Project getProjectById(long projectId);
    
    TaskList getAllTasksByUserId(Integer workingNumber);
    
    EmployeeList getAllWorkersManagersByWorkerWorkingNumber(Integer workingNumber);
    
    void assignEmployeesToProject(ArrayList<Integer> addedEmployees, Long id);
    
    void dismissEmployeesFromProject(ArrayList<Integer> removedEmployees, Long id);
    
    void updateEmployee(Employee employee);
    
    void changePassword(Employee employee, String password);
    
    void addNotificationListener(PropertyChangeListener listener);
    

}
