package mediator;

import model.*;

import java.rmi.RemoteException;

public interface ClientInterface {

    TaskList getAllTasksOfProject(Long id) ;

    ProjectList getAllProjectsByWorkingNumber(Integer workingNumber);

    void saveProject(Project project);

    void saveTask(Task task);

    void assignWorkerToTask(Integer workingNumber, Long taskID);

    String hello();
}
