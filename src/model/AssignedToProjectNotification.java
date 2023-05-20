package model;

import java.io.Serializable;

public class AssignedToProjectNotification implements Serializable, IdObject {
    private Long id;
    private Integer workingNumber;
    private Long projectId;
    private String projectName;

    public AssignedToProjectNotification(Long id, Integer workingNumber, Long projectId) {
        this.id = id;
        this.workingNumber = workingNumber;
        this.projectId = projectId;
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
