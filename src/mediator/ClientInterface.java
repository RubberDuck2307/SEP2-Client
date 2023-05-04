package mediator;

import model.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface {

    TaskList getAllTasksOfProject(Long id) ;

    ProjectList getAllProjectsByWorkingNumber(Integer workingNumber);
    ArrayList<Employee> getEmployeesAssignedToManager(int managerNumber);

    void saveProject(Project project);

    void saveTask(Task task);

    void assignWorkerToTask(Integer workingNumber, Long taskID);

    String hello();
}
