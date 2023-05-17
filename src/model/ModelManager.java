package model;


import mediator.ClientInterface;

import java.util.ArrayList;

public class ModelManager implements Model
{

    private Employee user;
    private ClientInterface client;

    public ModelManager(ClientInterface client)
    {
        this.client = client;
    }

    @Override public Long saveTag(Tag tag)
    {
        return client.saveTag(tag);
    }

    @Override public TagList getAllTags()
    {
        return client.getAllTags();
    }

    @Override public TagList getTagsOfTask(Long taskId)
    {
        return client.getTagsOfTask(taskId);
    }

    @Override public void addTagToTask(Long taskId, Long tagId)
    {
        client.addTagToTask(taskId, tagId);
    }

    @Override public void removeTagFromTask(Long taskId, Long tagId)
    {
        client.removeTagFromTask(taskId, tagId);
    }

    @Override public Tag getTag(Long tagId)
    {
        return client.getTag(tagId);
    }

    @Override public void deleteTag(Long id)
    {
        client.deleteTag(id);
    }

    @Override
    public TaskList getAllTasksOfProject(Long id)
    {
        return client.getAllTasksOfProject(id);
    }

    @Override
    public ProjectList getAllProjectsByWorkingNumber(Integer workingNumber)
    {
        return client.getAllProjectsByWorkingNumber(workingNumber);
    }

    @Override
    public ProjectList getAllProjects()
    {
        return client.getAllProjects();
    }

    @Override
    public EmployeeList getEmployeesAssignedToManager(int managerNumber)
    {
        return client.getEmployeesAssignedToManager(managerNumber);
    }

    public void changeTaskStatus(Long taskId, String status)
    {
        client.changeTaskStatus(taskId, status);
    }

    @Override
    public void removeWorkerFromTask(Integer workingNumber, Long taskID)
    {
        client.removeWorkerFromTask(workingNumber, taskID);
    }

    @Override
    public Employee login(UserProfile userProfile)
    {
        Employee employee = client.login(userProfile);
        return employee;
    }

    @Override
    public void assignWorkerToTask(Integer workingNumber, Long taskID)
    {
        client.assignWorkerToTask(workingNumber, taskID);
    }

    @Override
    public EmployeeList getAllEmployeesAssignedToProject(Long projectId)
    {
        return client.getAllEmployeesAssignedToProject(projectId);
    }

    @Override
    public void assignEmployeesToTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID)
    {
        client.assignEmployeesToTask(employeeWorkingNumbers, TaskID);
    }

    @Override
    public void assignEmployeeToProject(Integer workingNumber, Long projectID)
    {
        client.assignEmployeeToProject(workingNumber, projectID);
    }

    @Override
    public void removeEmployeeFromProject(Integer workingNumber, Long projectID)
    {
        client.removeEmployeeFromProject(workingNumber, projectID);
    }

    @Override
    public EmployeeList getAllProjectManagers()
    {
        return client.getAllProjectManagers();
    }

    @Override
    public Long saveProject(Project project)
    {
        return client.saveProject(project);
    }
    @Override
    public void updateProject(Project project)
    {
        client.updateProject(project);
    }

    @Override
    public EmployeeList getAllWorkers()
    {
        return client.getAllWorkers();
    }

    @Override
    public EmployeeList getEmployeesOfTask(Long taskId)
    {
        return client.getEmployeesOfTask(taskId);
    }

    public void unassignEmployeesFromTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID)
    {
        client.unassignEmployeesFromTask(employeeWorkingNumbers, TaskID);
    }

    @Override
    public Long saveTask(Task task)
    {
        return client.saveTask(task);
    }

    @Override
    public void assignWorkerToManager(int managerNumber, int workerNumber)
    {
        client.assignWorkerToManager(managerNumber, workerNumber);
    }

    @Override
    public void removeWorkerFromManager(int managerNumber, int workerNumber)
    {
        client.removeWorkerFromManager(managerNumber, workerNumber);
    }

    @Override
    public void updateTask(Task task)
    {
        client.updateTask(task);
    }

    @Override
    public Task getTask(Long projectId)
    {
        return client.getTask(projectId);
    }

    @Override
    public EmployeeList getAllEmployees()
    {
        return client.getAllEmployees();
    }

    @Override
    public Employee getEmployeeByWorkingNumber(int workingNumber)
    {
        return client.getEmployeeByWorkingNumber(workingNumber);
    }

    @Override
    public Project getProjectById(long projectId)
    {
        return client.getProjectById(projectId);
    }

    @Override
    public TaskList getAllTasksByUserId(Integer workingNumber)
    {
        return client.getAllTasksByUserId(workingNumber);
    }

    @Override
    public EmployeeList getAllWorkersManagersByWorkerWorkingNumber(Integer workingNumber)
    {
        return client.getAllWorkersManagersByWorkerWorkingNumber(workingNumber);
    }
    @Override
    public void assignEmployeesToProject(ArrayList<Integer> addedEmployees, Long id)
    {
        client.assignEmployeesToProject(addedEmployees, id);
    }
    @Override
    public void dismissEmployeesFromProject(ArrayList<Integer> removedEmployees, Long id)
    {
        client.dismissEmployeesFromProject(removedEmployees, id);
    }

    @Override
    public void updateEmployee(Employee employee)
    {
        client.updateEmployee(employee);
    }

    @Override
    public void changePassword(Employee employee, String password)
    {
        client.changePassword(employee, password);
    }

    @Override
    public Integer saveEmployee(Employee employee, String password)
    {
        return client.saveEmployee(employee, password);
    }
    
    @Override
    public void deleteEmployeeByWorkingNumber(Integer workingNumber)
    {
        client.deleteEmployeeByWorkingNumber(workingNumber);
    }

    public void setUser(Employee user)
    {
        this.user = user;
    }

    public Employee getUser()
    {
        return user;
    }
}
