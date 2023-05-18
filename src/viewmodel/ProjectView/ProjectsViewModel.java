package viewmodel.ProjectView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import model.*;
import viewmodel.TaskView.TasksTable;
import viewmodel.TaskView.WorkersTable;
import viewmodel.ViewModel;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import static javafx.application.Platform.runLater;

public class ProjectsViewModel extends ViewModelWithNavigationMenu
{
    private StringProperty titleProperty;
    private StringProperty descriptionProperty;
    private ProjectList projectList;
    private ObservableList<ProjectManagersTable> projectManagersTables;
    private ViewState viewState;
    private BooleanProperty selectedProject;

    public ProjectsViewModel(Model model, ViewState viewState) {
        super(model);
        this.viewState = viewState;
        titleProperty = new SimpleStringProperty("Description");
        descriptionProperty = new SimpleStringProperty("Select a project to see the description.");
        projectList = new ProjectList();
        projectManagersTables = FXCollections.observableArrayList();
        selectedProject = new SimpleBooleanProperty(false);


    }

    public void reset(){
        super.reset();
        descriptionProperty.setValue("Select a project to see the description.");
        titleProperty.setValue("Description");
        selectedProject.set(false);
        projectManagersTables.clear();
        load();
    }
    public void load(){
        super.load();

        if (model.getUser().getRole().equals(EmployeeRole.PROJECT_MANAGER) || model.getUser().getRole().equals(EmployeeRole.WORKER))
        {
            projectList = model.getAllProjectsByWorkingNumber(model.getUser().getWorkingNumber());
        }
        else
        {
            projectList = model.getAllProjects();
        }

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
        selectedProject.set(true);
    }

    public ProjectList getProjectList() {
        return projectList;
    }

    public BooleanProperty selectedProjectProperty() {
        return selectedProject;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }

}
