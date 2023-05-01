package viewmodel.ProjectView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import model.*;
import viewmodel.TaskView.TasksTable;
import viewmodel.TaskView.WorkersTable;
import viewmodel.ViewModel;
import viewmodel.ViewState;

import java.time.LocalDate;
import java.util.ArrayList;

import static javafx.application.Platform.runLater;

public class ProjectsViewModel implements ViewModel
{
    private Model model;
    private StringProperty titleProperty;
    private StringProperty descriptionProperty;
    private ProjectList projectList;
    private ObservableList<ProjectsTable> projectsObservableList;
    private ObservableList<Project> projects;
    private ObservableList<ProjectManagersTable> projectManagersTables;
    private ProjectsTable projectsTable;
    private ViewState viewState;
    public ProjectsViewModel(Model model, ViewState viewState) {
        this.viewState = viewState;
        this.model = model;
        titleProperty = new SimpleStringProperty();
        descriptionProperty = new SimpleStringProperty();
        projectList = new ProjectList();
        projectsObservableList = FXCollections.observableArrayList();
        projectManagersTables = FXCollections.observableArrayList();
        projects = FXCollections.observableArrayList();
        load();
    }

    public void load(){
        projectList = model.getAllProjectsByWorkingNumber(1);
        System.out.println(projectList);
        for (int i = 0; i < projectList.size(); i++) {
            projectsObservableList.add(new ProjectsTable(projectList.get(i)));
            projects.add(projectList.get(i));
        }
    }
    public Button getButton(){
        return projectsTable.getBtton();
    }

    public StringProperty getTitleProperty() {
        return titleProperty;
    }

    public ObservableList<ProjectsTable> getProjectsObservableList() {
        return projectsObservableList;
    }

    public ObservableList<ProjectManagersTable> getProjectManagersObservableList()
    {
        return projectManagersTables;
    }

    public void setProjectsObservableList(ObservableList<ProjectsTable> projectsObservableList) {
        this.projectsObservableList = projectsObservableList;
    }

    public StringProperty getDescriptionProperty() {
        return descriptionProperty;
    }

    public void setProject(Long id){
        Project project = projectList.getProjectByID(id);
        titleProperty.setValue(project.getName());
        descriptionProperty.setValue(project.getDescription());
        viewState.setProject(project);
        projectManagersTables.clear();
        for (int i = 0; i < project.getProjectManager().size(); i++)
        {
            projectManagersTables.add(new ProjectManagersTable(project.getProjectManager().get(i)));
        }
    }


    public ObservableList<Project> getProjects() {
        return projects;
    }

    public void openView(){

    }
}
