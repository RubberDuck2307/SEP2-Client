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

    void saveProject(Project project);

    void saveTask(Task task);

    void assignWorkerToTask(Integer workingNumber, Long taskID);

    EmployeeList getEmployeesOfTask(Long taskId);
    String hello();
}
