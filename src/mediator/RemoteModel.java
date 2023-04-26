package mediator;

import model.ProjectList;
import model.TaskList;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteModel extends Remote {

    TaskList getAllTasksOfProject(Long id) throws RemoteException;

    ProjectList getAllProjectsByWorkingNumber(Integer workingNumber) throws RemoteException;

    String hello() throws RemoteException;
}
