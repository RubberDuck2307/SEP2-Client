package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A class representing a project
 */

public class Project implements Serializable {
    /**
     * unique id of the project
     */
    private Long id;
    /**
     * name of the project
     */
    private String name;
    /**
     * description of the project
     */
    private String description;
    /**
     * deadline of the project
     */
    private LocalDate deadline;


    /**
     * 4-argument constructor for the class Project.
     * @param id
     * @param name
     * @param description
     * @param deadline
     */

    public Project(Long id, String name, String description, LocalDate deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * 3-argument constructor for the class Project. Id is not set.
     * @param name
     * @param description
     * @param deadline
     */

    public Project(String name, String description, LocalDate deadline) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
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


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}
