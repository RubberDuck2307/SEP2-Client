package mediator;

import model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteModel extends Remote {

    Long saveTag(Tag tag) throws RemoteException;
    TagList getAllTags() throws RemoteException;
    TagList getTagsOfTask(Long taskId) throws RemoteException;
    void addTagToTask(Long taskId, Long tagId) throws RemoteException;
    void removeTagFromTask(Long taskId, Long tagId) throws RemoteException;
    Tag getTag(Long tagId) throws RemoteException;
    void deleteTag(Long id) throws RemoteException;

    TaskList getAllTasksOfProject(Long id) throws RemoteException;

    ProjectList getAllProjectsByWorkingNumber(Integer workingNumber) throws RemoteException;

    Long saveTask(Task task) throws RemoteException;

    ProjectList getAllProjects() throws RemoteException;

    void changeTaskStatus(Long taskId, String status) throws RemoteException;

    Long saveProject(Project project) throws RemoteException;

    void unassignEmployeesFromTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID) throws RemoteException;

    void dismissEmployeesFromProject(ArrayList<Integer> employeeWorkingNumbers, Long projectID) throws RemoteException;

    void assignEmployeesToProject(ArrayList<Integer> addedEmployees, Long id) throws RemoteException;

    EmployeeList getEmployeesAssignedToManager(int managerNumber) throws RemoteException;

    Task getTask(Long projectId) throws RemoteException;

    Employee login(UserProfile userProfile) throws RemoteException;

    void assignWorkerToTask(Integer workingNumber, Long taskID) throws RemoteException;

    void removeWorkerFromTask(Integer workingNumber, Long taskID) throws RemoteException;

    void assignEmployeesToTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID) throws RemoteException;

    Integer saveEmployee(Employee employee, String password) throws RemoteException;

    void assignEmployeeToProject(Integer workingNumber, Long projectID) throws RemoteException;

    void removeEmployeeFromProject(Integer workingNumber, Long projectID) throws RemoteException;

    void assignWorkerToManager(int managerNumber, int workerNumber) throws RemoteException;

    void removeWorkerFromManager(int managerNumber, int workerNumber) throws RemoteException;

    EmployeeList getAllProjectManagers() throws RemoteException;

    EmployeeList getAllWorkers() throws RemoteException;

    EmployeeList getAllEmployeesAssignedToProject(Long projectId) throws RemoteException;

    void updateProject(Project project) throws RemoteException;

    String hello() throws RemoteException;
    NoteList getAllNotesSavedByEmployee(Integer workingNumber) throws RemoteException;

    void updateTask(Task task) throws RemoteException;

    EmployeeList getEmployeesOfTask(Long taskId) throws RemoteException;

    Employee getEmployeeByWorkingNumber(int workingNumber) throws RemoteException;

    EmployeeList getAllEmployees() throws RemoteException;

    Project getProjectById(long projectId) throws RemoteException;

    TaskList getAllTasksByUserId(Integer workingNumber) throws RemoteException;

    EmployeeList getAllWorkersManagersByWorkerWorkingNumber(Integer workingNumber) throws RemoteException;
    void updateEmployee(Employee employee) throws RemoteException;
    void changePassword(Employee employee, String password) throws RemoteException;
}
