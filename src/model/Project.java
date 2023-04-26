package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Project implements Serializable {
    private Long id;
    private String name;
    private String description;
    private LocalDate deadline;
    private ArrayList<Employee> projectManager;

    public Project(Long id, String name, String description, LocalDate deadline, ArrayList<Employee> projectManager) {
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

    public ArrayList<Employee> getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(ArrayList<Employee> projectManager) {
        this.projectManager = projectManager;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", projectManager=" + projectManager +
                '}';
    }
}
