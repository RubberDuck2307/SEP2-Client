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
    private ObservableList<ProjectManagersTable> projectManagersTables;
    private ViewState viewState;
    public ProjectsViewModel(Model model, ViewState viewState) {
        this.viewState = viewState;
        this.model = model;
        titleProperty = new SimpleStringProperty();
        descriptionProperty = new SimpleStringProperty();
        projectList = new ProjectList();
        projectManagersTables = FXCollections.observableArrayList();

    }

    public void load(){
        projectList = model.getAllProjectsByWorkingNumber(1);
    }

    public StringProperty getTitleProperty() {
        return titleProperty;
    }


    public ObservableList<ProjectManagersTable> getProjectManagersObservableList()
    {
        return projectManagersTables;
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
        EmployeeList employeeList = model.getAllEmployeesAssignedToProject(id);
        for (int i = 0; i < employeeList.size(); i++)
        {
            projectManagersTables.add(new ProjectManagersTable(employeeList.get(i)));
        }
    }

    public ProjectList getProjectList() {
        return projectList;
    }
}
