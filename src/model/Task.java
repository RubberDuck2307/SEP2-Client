package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Task implements Serializable {
    private Long id;
    private String name;
    private String description;
    private LocalDate deadline;
    private Integer estimatedTime;
    private String priority;
    private String status;
    private Long projectId;
    private LocalDate startingDate;

    private EmployeeList workers;

    public Task(Long id, String name, String description, LocalDate deadline, int estimatedTime, String priority, String status, Long projectId, LocalDate startingDate, EmployeeList workers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.estimatedTime = estimatedTime;
        this.priority = priority;
        this.status = status;
        this.projectId = projectId;
        this.startingDate = startingDate;
        this.workers = workers;
    }

    public Task(String name, String description, LocalDate deadline, Integer estimatedTime, String priority, String status, Long projectId, LocalDate startingDate) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.estimatedTime = estimatedTime;
        this.priority = priority;
        this.status = status;
        this.projectId = projectId;
        this.startingDate = startingDate;
        workers = new EmployeeList();
    }

    public Task(Long id, String name, String description, LocalDate deadline, int estimatedTime, String priority, String status, Long projectId, LocalDate startingDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.estimatedTime = estimatedTime;
        this.priority = priority;
        this.status = status;
        this.projectId = projectId;
        this.startingDate = startingDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long serverId) {
        this.projectId = serverId;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public EmployeeList getWorkers() {
        return workers;
    }

    public void setWorkers(EmployeeList workers) {
        this.workers = workers;
    }

    public boolean equals(Object object){
        if (object instanceof Task){
            Task task = (Task) object;
            return task.getId().equals(this.getId()) &&
                    task.getStatus().equals(this.getStatus()) &&
                    task.getPriority().equals(this.getPriority()) &&
                    task.getEstimatedTime() == this.getEstimatedTime() &&
                    task.getDeadline().equals(this.getDeadline()) &&
                    task.getStartingDate().equals(this.getStartingDate()) &&
                    task.getDescription().equals(this.getDescription()) &&
                    task.getName().equals(this.getName()) &&
                    task.getProjectId().equals(this.getProjectId());
        }
        return false;
    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", estimatedTime=" + estimatedTime +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", projectId=" + projectId +
                ", startingDate=" + startingDate +
                ", workers=" + workers +
                '}';
    }


}