package viewmodel;

import javafx.beans.property.*;
import model.Employee;
import model.Model;
import model.Project;
import model.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectsViewModel implements ViewModel
{
    private StringProperty titleProperty;
    private StringProperty descriptionProperty;
    private ObjectProperty<LocalDate> deadlineProperty;
    private StringProperty projectManagerProperty;

    private ArrayList<Employee> employees;
    private ArrayList<Task> projectTasks;
    private StringProperty error;

    public ProjectsViewModel(Model model) {
        this.error = new SimpleStringProperty();
        this.projectTasks = new ArrayList<>();
        this.employees= new ArrayList<>();
        this.projectManagerProperty=new SimpleStringProperty();
        this.deadlineProperty=new SimpleObjectProperty<>();
        this.descriptionProperty=new SimpleStringProperty();
        this.titleProperty=new SimpleStringProperty();
        //titleProperty = new SimpleStringProperty(project.getTitle());
        //descriptionProperty = new SimpleStringProperty(project.getDescription());
        //deadlineProperty = new SimpleObjectProperty<>(project.getDeadline());
        //projectManagerProperty = new SimpleStringProperty(project.getProjectManager().getFirstName() + " " + project.getProjectManager().getLastName() + " " + project.getProjectManager().getPhoneNumber());
    }


    public StringProperty titleProperty() {
        return titleProperty;
    }

    public StringProperty descriptionProperty() {
        return descriptionProperty;
    }

    public LocalDate getDeadlineProperty() {
        return deadlineProperty.get();
    }

    public StringProperty projectManagerProperty() {
        return projectManagerProperty;
    }

    public ObjectProperty<LocalDate> deadlineProperty() {
        return deadlineProperty;
    }

    public void setDeadlineProperty(LocalDate deadlineProperty) {
        this.deadlineProperty.set(deadlineProperty);
    }

    public ArrayList<Employee> getEmployeesWithAccess() {
        Project project = null;
        return new ArrayList<>();//project.getEmployeesWithAccess());
    }

}
