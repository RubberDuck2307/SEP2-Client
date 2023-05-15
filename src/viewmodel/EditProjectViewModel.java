package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import model.Employee;
import model.EmployeeList;
import model.Model;
import model.Project;
import util.Validator;
import viewmodel.AddProjectView.AssignManagersTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class EditProjectViewModel implements ViewModel
{
    private Validator validator;
    private ObjectProperty<Image> avatarPic;
    private ObjectProperty<Employee> employee;
    private StringProperty titleProperty;
    private StringProperty titleEProperty;
    private SimpleObjectProperty<LocalDate> deadlineProperty;
    private StringProperty deadlineEProperty;
    private StringProperty descriptionProperty;
    private ObservableList<AssignManagersTable> assignManagersObservableList;
    private AssignManagersTable assignManagersTable;
    private EmployeeList employeesList;
    private ObservableList<Employee> employees;
    private StringProperty errorProperty;
    private EmployeeList managers;
    private EmployeeList assignedManagers;
    private EmployeeList originalAssignedEmployees;
    private EmployeeList assignedEmployees;
    private StringProperty name;
    private StringProperty workingNumber;
    private ViewState viewState;
    private Model model;
    
    public EditProjectViewModel(Model model, ViewState viewState)
    {
        this.model = model;
        this.viewState = viewState;
        this.employee = new SimpleObjectProperty<>();
        this.titleProperty = new SimpleStringProperty();
        this.descriptionProperty = new SimpleStringProperty();
        this.titleEProperty = new SimpleStringProperty();
        this.deadlineEProperty = new SimpleStringProperty();
        this.deadlineProperty = new SimpleObjectProperty<>();
        this.avatarPic = new SimpleObjectProperty<>();
        managers = new EmployeeList();
        assignedManagers = new EmployeeList();
        this.validator = new Validator();
        employeesList = new EmployeeList();
        this.name = new SimpleStringProperty();
        this.workingNumber = new SimpleStringProperty();
        assignManagersObservableList = FXCollections.observableArrayList();
        employees = FXCollections.observableArrayList();
        assignedEmployees = new EmployeeList();
        originalAssignedEmployees = new EmployeeList();
    }
    
    public void load()
    {
        titleProperty.setValue(viewState.getProject().getName());
        deadlineProperty.setValue(viewState.getProject().getDeadline());
        descriptionProperty.setValue(viewState.getProject().getDescription());
        employee.setValue(model.getUser());
        setAvatarPicture();
        originalAssignedEmployees = model.getEmployeesOfTask(viewState.getTask().getId());
        assignedEmployees = model.getEmployeesOfTask(viewState.getTask().getId());
        assignedManagers = model.getAllEmployeesAssignedToProject(viewState.getProject().getId());
        managers = model.getAllProjectManagers();
        name.setValue(this.model.getUser().getName());
        workingNumber.setValue(this.model.getUser().getWorkingNumber().toString());
    }
    
    public void reset()
    {
        titleProperty.setValue("");
        descriptionProperty.setValue("");
        deadlineProperty.setValue(null);
        load();
    }
    
    public boolean saveProject()
    {
        Project project1 = viewState.getProject();
        Boolean valid = true;
        try
        {
            validator.validateTitle(titleProperty.getValue());
            titleProperty.setValue(project1.getName());
        }
        catch (Exception e)
        {
            valid = false;
            titleEProperty.setValue(e.getMessage());
        }
        try
        {
            validator.validateDeadline(deadlineProperty.get());
            deadlineProperty.setValue(project1.getDeadline());
        }
        catch (Exception e)
        {
            valid = false;
            deadlineEProperty.setValue(e.getMessage());
        }
        descriptionProperty.setValue(project1.getDescription());
        if (valid)
        {
            Project project2 = new Project(project1.getId(), titleProperty.getValue(), descriptionProperty.getValue(), deadlineProperty.getValue());
            model.updateProject(project2);
        }
        
        ArrayList<Integer> addedEmployees = new ArrayList<>();
        ArrayList<Integer> removedEmployees = new ArrayList<>();
        
        if (assignedEmployees.size() != 0) {
            for (int i = 0; i < assignedEmployees.size(); i++) {
                
                if (!originalAssignedEmployees.containsByWorkingNumber(assignedEmployees.get(i).getWorkingNumber())) {
                    addedEmployees.add(assignedEmployees.get(i).getWorkingNumber());
                }
            }
            for (int i = 0; i < originalAssignedEmployees.size(); i++) {
                if (!assignedEmployees.containsByWorkingNumber(originalAssignedEmployees.get(i).getWorkingNumber())) {
                    removedEmployees.add(originalAssignedEmployees.get(i).getWorkingNumber());
                }
            }
        } else {
            for (int i = 0; i < originalAssignedEmployees.size(); i++) {
                removedEmployees.add(originalAssignedEmployees.get(i).getWorkingNumber());
            }
        }
        
        if (addedEmployees.size() != 0) {
            model.assignEmployeesToProject(addedEmployees, viewState.getProject().getId());
        }
        if (removedEmployees.size() != 0) {
            model.dismissEmployeesFromProject(removedEmployees, viewState.getProject().getId());
        }
        return valid;
    }
    
    public void switchWorker(Employee employee)
    {
        if (assignedManagers.containsByWorkingNumber(employee.getWorkingNumber()))
        {
            assignedManagers.removeByWorkingNumber(employee.getWorkingNumber());
        }
        else
        {
            assignedManagers.addEmployee(employee);
        }
    }
    
    public boolean isEmployeeAssigned(Employee employee)
    {
        return assignedManagers.containsByWorkingNumber(employee.getWorkingNumber());
    }
    
    public StringProperty getTitleProperty()
    {
        return titleProperty;
    }
    
    public StringProperty getDescriptionProperty()
    {
        return descriptionProperty;
    }
    
    public StringProperty getTitleErrorProperty()
    {
        return titleEProperty;
    }
    
    public StringProperty getDeadlineErrorProperty()
    {
        return deadlineEProperty;
    }
    
    public SimpleObjectProperty<LocalDate> getDeadlineProperty()
    {
        return deadlineProperty;
    }
    
    public EmployeeList getManagers()
    {
        return managers;
    }
    
    public String getName()
    {
        return name.get();
    }
    public StringProperty nameProperty()
    {
        return name;
    }
    public String getWorkingNumber()
    {
        return workingNumber.get();
    }
    public StringProperty workingNumberProperty()
    {
        return workingNumber;
    }
    public boolean isWoman()
    {
        return Objects.equals(employee.getValue().getGender(), "F");
    }
    public void setAvatarPicture()
    {
        if (isWoman())
        {
            avatarPic.setValue(new Image("/icons/woman-avatar.png"));
        }
        else
        {
            avatarPic.setValue(new Image("/icons/man-avatar.png"));
        }
    }
    public ObjectProperty<Image> avatarPicProperty()
    {
        return avatarPic;
    }
}
