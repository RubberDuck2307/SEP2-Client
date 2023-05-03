package model;

public interface Model {

    TaskList getAllTasksOfProject(Long id) ;

    ProjectList getAllProjectsByWorkingNumber(Integer workingNumber);

    void assignWorkerToTask(Integer workingNumber, Long taskID);

    void saveProject(Project project);

    void saveTask(Task task);
}
