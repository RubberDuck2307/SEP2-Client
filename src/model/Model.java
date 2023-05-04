package model;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Model {

    TaskList getAllTasksOfProject(Long id) ;

    ProjectList getAllProjectsByWorkingNumber(Integer workingNumber);

    EmployeeList getEmployeesAssignedToManager(int managerNumber);
    public void removeWorkerFromTask(Integer workingNumber, Long taskID);

    void assignWorkerToTask(Integer workingNumber, Long taskID);
    EmployeeList getAllEmployeesAssignedToProject(Long projectId);
    void saveProject(Project project);
    EmployeeList getEmployeesOfTask(Long taskId);
    void saveTask(Task task);
}
