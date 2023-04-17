package model;

import java.util.ArrayList;
import java.util.Objects;

public class ProjectList {

    ArrayList<Project> projects;

    public ProjectList(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ProjectList(){
        this.projects = new ArrayList<>();
    }

    public Project getProjectByID(Long id){
        for(Project project: projects){
            if (Objects.equals(project.getId(), id)){
                return project;
            }
        }
        throw new RuntimeException("Project with such id has not been found");
    }

    public void addProject(Project project){
        projects.add(project);
    }

}
