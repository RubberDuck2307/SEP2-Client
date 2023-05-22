package mediator;

import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;

import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class Client implements ClientInterface, RemoteListener<String, String> {

    private RemoteModel model;
    private ListenerHandler listenerHandler;

    public Client() throws MalformedURLException, NotBoundException, RemoteException {

        model = (RemoteModel) Naming.lookup("rmi://localhost:1099/Case");
        UnicastRemoteObject.exportObject(this, 0);
        this.listenerHandler = new ListenerHandler(model, this);
    }


    @Override
    public IdObjectList<ForgottenPasswordNotification> getForgottenPasswordNotification() {
        try {
            return model.getForgottenPasswordNotification();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IdObjectList<AssignedToTaskNotification> getAssignedToTaskNotification(Integer workingNumber) {
        try {
            return model.getAssignedToTaskNotification(workingNumber);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IdObjectList<AssignedToProjectNotification> getAssignedToProjectNotification(Integer workingNumber) {
        try {
            return model.getAssignedToProjectNotification(workingNumber);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long saveTag(Tag tag) {
        try {
            return model.saveTag(tag);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TagList getAllTags() {
        try {
            return model.getAllTags();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TagList getTagsOfTask(Long taskId) {
        try {
            return model.getTagsOfTask(taskId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addTagToTask(Long taskId, Long tagId) {
        try {
            model.addTagToTask(taskId, tagId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeTagFromTask(Long taskId, Long tagId) {
        try {
            model.removeTagFromTask(taskId, tagId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Tag getTag(Long tagId) {
        try {
            return model.getTag(tagId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTag(Long id) {
        try {
            model.deleteTag(id);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addNotificationListener(PropertyChangeListener listener) {
        listenerHandler.addListener("notification", listener);
    }

    @Override
    public TaskList getAllTasksOfProject(Long id) {
        try {
            return model.getAllTasksOfProject(id);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void assignWorkerToTask(Integer workingNumber, Long taskID) {
        try {
            model.assignWorkerToTask(workingNumber, taskID);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeWorkerFromTask(Integer workingNumber, Long taskID) {
        try {
            model.removeWorkerFromTask(workingNumber, taskID);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addForgetPasswordNotification(Integer workingNumber) {
        try {
            return model.addForgetPasswordNotification(workingNumber);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee login(UserProfile userProfile) {
        try {
            Employee employee = model.login(userProfile);
            listenerHandler.addServerListener(employee);
            return employee;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    public Task getTask(Long projectId) {
        try {
            return model.getTask(projectId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeList getEmployeesOfTask(Long taskId) {
        try {
            return model.getEmployeesOfTask(taskId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProjectList getAllProjectsByWorkingNumber(Integer workingNumber) {
        try {
            return model.getAllProjectsByWorkingNumber(workingNumber);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProjectList getAllProjects() {
        try {
            return model.getAllProjects();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    public void changeTaskStatus(Long taskId, String status) {
        try {
            model.changeTaskStatus(taskId, status);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeList getEmployeesAssignedToManager(
            int managerNumber) {
        try {
            return model.getEmployeesAssignedToManager(managerNumber);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public EmployeeList getAllEmployeesAssignedToProject(Long projectId) {
        try {
            return model.getAllEmployeesAssignedToProject(projectId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer saveEmployee(Employee employee, String password) {
        try {
            return model.saveEmployee(employee, password);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void deleteEmployeeByWorkingNumber(Integer workingNumber)
    {
        try
    {
        model.deleteEmployeeByWorkingNumber(workingNumber);
    }
    catch (RemoteException e)
    {
        throw new RuntimeException(e);
    }
    }

    @Override
    public void assignEmployeesToTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID) {
        try {
            model.assignEmployeesToTask(employeeWorkingNumbers, TaskID);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeList getAllProjectManagers() {
        try {
            return model.getAllProjectManagers();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeList getAllWorkers() {
        try {
            return model.getAllWorkers();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void assignEmployeeToProject(Integer workingNumber,
                                        Long projectID) {
        try {
            model.assignEmployeeToProject(workingNumber, projectID);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeEmployeeFromProject(Integer workingNumber,
                                          Long projectID) {
        try {
            model.removeEmployeeFromProject(workingNumber, projectID);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long saveProject(Project project) {
        try {
            return model.saveProject(project);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProject(Project project) {
        try {
            model.updateProject(project);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void unassignEmployeesFromTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID) {
        try {
            model.unassignEmployeesFromTask(employeeWorkingNumbers, TaskID);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void dismissEmployeesFromProject(ArrayList<Integer> employeeWorkingNumbers, Long projectID) {
        try {
            model.dismissEmployeesFromProject(employeeWorkingNumbers, projectID);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void assignEmployeesToProject(ArrayList<Integer> addedEmployees, Long id) {
        try {
            model.assignEmployeesToProject(addedEmployees, id);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long saveTask(Task task) {
        try {
            return model.saveTask(task);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteTaskById(Long id)
    {
    }
    
    @Override
    public void assignWorkerToManager(int managerNumber,
                                      int workerNumber) {
        try {
            model.assignWorkerToManager(managerNumber, workerNumber);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeWorkerFromManager(int managerNumber,
                                        int workerNumber) {
        try {
            model.removeWorkerFromManager(managerNumber, workerNumber);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public String hello() {
        try {
            return model.hello();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTask(Task task) {
        try {
            model.updateTask(task);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeList getAllEmployees() {
        try {
            return model.getAllEmployees();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee getEmployeeByWorkingNumber(int workingNumber) {
        try {
            return model.getEmployeeByWorkingNumber(workingNumber);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Project getProjectById(long projectId) {
        try {
            return model.getProjectById(projectId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TaskList getAllTasksByUserId(Integer workingNumber) {
        try {
            return model.getAllTasksByUserId(workingNumber);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeList getAllWorkersManagersByWorkerWorkingNumber(
            Integer workingNumber) {
        try {
            return model.getAllWorkersManagersByWorkerWorkingNumber(workingNumber);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        try {
            model.updateEmployee(employee);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changePassword(Employee employee, String password) {
        try {
            model.changePassword(employee, password);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void propertyChange(ObserverEvent<String, String> event) throws RemoteException {
        listenerHandler.handlePropertyChange(event);
    }

    public void logOut(){

            listenerHandler.removeServerListener();
    }
}
