package model;

public interface Model {

    TaskList getAllTasksOfProject(Long id) ;

    ProjectList getAllProjectsByWorkingNumber(Integer workingNumber);

    void saveProject(Project project);

    void saveTask(Task task);
}
