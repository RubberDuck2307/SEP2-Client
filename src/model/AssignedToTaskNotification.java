package model;

import java.io.Serializable;

/**
 * Class representing assigned to task notification.
 * @author Anna Andrlova, Alex Bolfa, Cosmin Demian, Jan Metela, Arturs Ricards Rijnieks
 * @version 1.0 - May 2023
 */
public class AssignedToTaskNotification implements Serializable, IdObject {
    /**
     * The id of the notification.
     */
    private Long id;
    /**
     * The working number of the employee who was assigned to a task.
     */
    private Integer workingNumber;
    /**
     * The id of the task.
     */
    private Long taskId;

    /**
     * The name of the task.
     */
    private String taskName;

    /**
     * 3-argument constructor for the class AssignedToTaskNotification. The task name is set to null
     * @param id
     * @param workingNumber
     * @param taskId
     */
    public AssignedToTaskNotification(Long id, Integer workingNumber, Long taskId) {
        this.id = id;
        this.workingNumber = workingNumber;
        this.taskId = taskId;
        this.taskName = null;
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
