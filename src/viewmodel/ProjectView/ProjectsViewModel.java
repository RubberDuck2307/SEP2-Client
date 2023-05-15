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
import viewmodel.ViewState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import static javafx.application.Platform.runLater;

public class ProjectsViewModel implements ViewModel
{
    private ObjectProperty<Employee> employee;
    private ObjectProperty<Image> avatarPic;
    private Model model;

    private StringProperty userName;
    private StringProperty userNumber;
    private ObjectProperty<Employee> employeeProperty;
    private StringProperty titleProperty;
    private StringProperty descriptionProperty;
    private ProjectList projectList;
    private ObservableList<ProjectManagersTable> projectManagersTables;
    private ViewState viewState;
    private BooleanProperty selectedProject;
    public ProjectsViewModel(Model model, ViewState viewState) {
        this.viewState = viewState;
        this.model = model;
        this.employee=new SimpleObjectProperty<>();
        this.avatarPic=new SimpleObjectProperty<>();
        titleProperty = new SimpleStringProperty("Description");
        descriptionProperty = new SimpleStringProperty("Select a project to see the description.");
        projectList = new ProjectList();
        projectManagersTables = FXCollections.observableArrayList();
        selectedProject = new SimpleBooleanProperty(false);
        employeeProperty = new SimpleObjectProperty<>();
        userName = new SimpleStringProperty();
        userNumber = new SimpleStringProperty();
    }

    public void reset(){
        descriptionProperty.setValue("Select a project to see the description.");
        titleProperty.setValue("Description");
        selectedProject.set(false);
        projectManagersTables.clear();
        load();
    }
    public void load(){
        employee.setValue(model.getUser());
        setAvatarPicture();
        if (model.getUser().getRole().equals(EmployeeRole.PROJECT_MANAGER) || model.getUser().getRole().equals(EmployeeRole.WORKER))
        {
            projectList = model.getAllProjectsByWorkingNumber(model.getUser().getWorkingNumber());
        }
        else
        {
            projectList = model.getAllProjects();
        }
        employeeProperty.set(model.getUser());
        userName.set(model.getUser().getName());
        userNumber.set(model.getUser().getWorkingNumber().toString());
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
    public boolean isSelectedProject() {
        return selectedProject.get();
    }

    public Employee getEmployeeProperty() {
        return employeeProperty.get();
    }

    public ObjectProperty<Employee> employeePropertyProperty() {
        return employeeProperty;
    }

    public BooleanProperty selectedProjectProperty() {
        return selectedProject;

    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public String getUserNumber() {
        return userNumber.get();
    }

    public StringProperty userNumberProperty() {
        return userNumber;
    }
    public ObjectProperty<Image> avatarPicProperty()
    {
        return avatarPic;
    }
    public boolean isWoman(){
        return Objects.equals(employee.getValue().getGender(), "F");
    }
    public void setAvatarPicture(){
        if(isWoman()){
            avatarPic.setValue(new Image("/icons/woman-avatar.png"));
        }
        else{
            avatarPic.setValue(new Image("/icons/man-avatar.png"));
        }
    }
}
