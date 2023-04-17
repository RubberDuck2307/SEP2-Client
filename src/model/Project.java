package model;

import java.time.LocalDate;

public class Project {
    private Long id;
    private String name;
    private String description;
    private LocalDate deadline;
    private Employee projectManager;

    public Project(Long id, String name, String description, LocalDate deadline, Employee projectManager) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.projectManager = projectManager;
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

    public Employee getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(Employee projectManager) {
        this.projectManager = projectManager;
    }
}
