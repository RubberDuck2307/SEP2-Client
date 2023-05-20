package model;

import java.io.Serializable;

public class AssignedToTaskNotification implements Serializable, IdObject {
    private Long id;
    private Integer workingNumber;
    private Long taskId;

    private String taskName;

    public AssignedToTaskNotification(Long id, Integer workingNumber, Long taskId) {
        this.id = id;
        this.workingNumber = workingNumber;
        this.taskId = taskId;
    }

    public Long getId() {
        return id;
    }

    public Integer getWorkingNumber() {
        return workingNumber;
    }

    public Long getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
