package mediator;

import model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteModel extends Remote {

    TaskList getAllTasksOfProject(Long id) throws RemoteException;
    ProjectList getAllProjectsByWorkingNumber(Integer workingNumber) throws RemoteException;

    void saveTask(Task task) throws RemoteException;

    void saveProject(Project project)throws RemoteException;

    void assignWorkerToTask(Integer workingNumber, Long taskID) throws RemoteException;

    Employee login(UserProfile userProfile) throws RemoteException;

    void saveEmployee(Employee employee) throws RemoteException;

    void updateProject(Project project) throws RemoteException;

    ArrayList<Employee> getEmployeesAssignedToManager(int managerNumber) throws RemoteException;
    String hello() throws RemoteException;
}
