package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class representing a list of projects.
 */
public class ProjectList implements Serializable {

    /**
     * ArrayList of projects
     */
    ArrayList<Project> projects;

    /**
     * Constructor that sets the ArrayList of projects.
     * @param projects
     */
    public ProjectList(ArrayList<Project> projects) {
        this.projects = projects;
    }

    /**
     * Zero-argument constructor that sets the ArrayList of projects to a new empty ArrayList.
     */

    public ProjectList(){
        this.projects = new ArrayList<>();
    }

    /**
     *
     * @param id
     * @return project with the given id
     * @throws RuntimeException if there is no project with such id in the list
     */

    public Project getProjectByID(Long id){
        for(Project project: projects){
            if (Objects.equals(project.getId(), id)){
                return project;
            }
        }
        throw new RuntimeException("Project with such id has not been found");
    }

    /**
     * Adds a project into the ArrayList projects.
     * @param project
     */
    public void addProject(Project project){
        projects.add(project);
    }

    /**
     * @return the size of the list
     */
    public int size(){
        return projects.size();
    }

    /**
     *
     * @param index
     * @return project with the given index
     */
    public Project get(int index){
        return projects.get(index);
    }

    /**
     * @param project
     * @return true if the list contains the given project and false if it does not
     */
    public boolean contains(Project project){
        return projects.contains(project);
    }
    @Override
    public String toString() {
        return "ProjectList{" +
                "projects=" + projects +
                '}';
    }
}
