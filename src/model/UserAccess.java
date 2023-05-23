package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class UserAccess {
    private BooleanProperty editTask;
    private BooleanProperty seeProject;
    private BooleanProperty seeWorkers;
    private BooleanProperty createTask;

    public UserAccess(Boolean editTask, Boolean seeProject, Boolean seeWorkers, Boolean createTask) {
        this.editTask = new SimpleBooleanProperty(editTask);
        this.seeProject = new SimpleBooleanProperty(seeProject);
        this.seeWorkers = new SimpleBooleanProperty(seeWorkers);
        this.createTask = new SimpleBooleanProperty(createTask);
    }


    public boolean isEditTask() {
        return editTask.get();
    }

    public BooleanProperty editTaskProperty() {
        return editTask;
    }

    public boolean isSeeProject() {
        return seeProject.get();
    }

    public BooleanProperty seeProjectProperty() {
        return seeProject;
    }

    public boolean isSeeWorkers() {
        return seeWorkers.get();
    }

    public BooleanProperty seeWorkersProperty() {
        return seeWorkers;
    }

    public boolean isCreateTask() {
        return createTask.get();
    }

    public BooleanProperty createTaskProperty() {
        return createTask;
    }
}
