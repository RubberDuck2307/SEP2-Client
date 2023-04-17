package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Task {
    private Long id;
    private String name;
    private String description;
    private LocalDate deadline;
    private int estimatedTime;
    private String priority;
    private String status;
    private Long projectId;
    private LocalDate startingDate;

    private ArrayList<Employee> workers;

    public Task(Long id, String name, String description, LocalDate deadline, int estimatedTime, String priority, String status, Long projectId, LocalDate startingDate, ArrayList<Employee> workers) {
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

    public ArrayList<Employee> getWorkers() {
        return workers;
    }

    public void setWorkers(ArrayList<Employee> workers) {
        this.workers = workers;
    }

}
