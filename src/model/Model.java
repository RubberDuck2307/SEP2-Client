package model;

import java.util.ArrayList;

public interface Model {

    TaskList getAllTasksOfProject(Long id) ;

    ProjectList getAllProjectsByWorkingNumber(Integer workingNumber);

    ArrayList<Employee> getEmployeesAssignedToManager(int managerNumber);

    void assignWorkerToTask(Integer workingNumber, Long taskID);

    void saveProject(Project project);

    void saveTask(Task task);
}
