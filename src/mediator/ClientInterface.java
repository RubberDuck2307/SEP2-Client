package mediator;

import model.ProjectList;
import model.TaskList;

import java.rmi.RemoteException;

public interface ClientInterface {

    TaskList getAllTasksOfProject(Long id) ;

    ProjectList getAllProjectsByWorkingNumber(Integer workingNumber);

    String hello();
}
