package mediator;

import model.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ClientInterface {

    TaskList getAllTasksOfProject(Long id) ;

    ProjectList getAllProjectsByWorkingNumber(Integer workingNumber);
    EmployeeList getEmployeesAssignedToManager(int managerNumber);
    void removeWorkerFromTask(Integer workingNumber, Long taskID);
    EmployeeList getAllEmployeesAssignedToProject(Long projectId);
    void assignEmployeesToTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID);
    EmployeeList getAllProjectManagers();

    void saveProject(Project project);
    void unassignEmployeesFromTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID);
    Long saveTask(Task task);
    
    Integer saveEmployee(Employee employee, String password);

    void assignWorkerToTask(Integer workingNumber, Long taskID);

    EmployeeList getEmployeesOfTask(Long taskId);
    String hello();
    void updateTask(Task task);
    NoteList getAllNotesSavedByEmployee(Integer workingNumber);
}
