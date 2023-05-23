package model;

import java.io.Serializable;

/**
 * Class representing assigned to project notification.
 * @author Anna Andrlova, Alex Bolfa, Cosmin Demian, Jan Metela, Arturs Ricards Rijnieks
 * @version 1.0 - May 2023
 */
public class AssignedToProjectNotification implements Serializable, IdObject {
    /**
     * The id of the notification.
     */
    private Long id;
    /**
     * The working number of the employee who were assigned to a project.
     */
    private Integer workingNumber;
    /**
     * The id of the project.
     */
    private Long projectId;
    /**
     * The name of the project.
     */
    private String projectName;

    /**
     * 3-argument constructor for the class AssignedToProjectNotification. The project name is set to null
     * @param id
     * @param workingNumber
     * @param projectId
     */
    public AssignedToProjectNotification(Long id, Integer workingNumber, Long projectId) {
        this.id = id;
        this.workingNumber = workingNumber;
        this.projectId = projectId;
        this.projectName = null;
    }

    public Long getId() {
        return id;
    }

    public Integer getWorkingNumber() {
        return workingNumber;
    }

    public Long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
