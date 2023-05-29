package viewmodel.ProjectView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import model.*;
import util.Validator;
import viewmodel.ViewModel;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class EditProjectViewModel extends ViewModelWithNavigationMenu
{
    private Validator validator;
    private StringProperty titleProperty;
    private StringProperty titleEProperty;
    private SimpleObjectProperty<LocalDate> deadlineProperty;
    private StringProperty deadlineEProperty;
    private StringProperty descriptionProperty;
    private StringProperty headline;
    private EmployeeList managers;
    private EmployeeList assignedManagers;
    private EmployeeList originalAssignedEmployees;
    private EmployeeList assignedEmployees;
    private ViewState viewState;
    private EmployeeList employeesOfManager;
    private EmployeeList employeesOfProject;
    private ObjectProperty<Employee> user;
    
    public EditProjectViewModel(Model model, ViewState viewState)
    {
        super(model);
        this.viewState = viewState;
        user = new SimpleObjectProperty<>();
        employeesOfProject = new EmployeeList();
        employeesOfManager = new EmployeeList();
        this.titleProperty = new SimpleStringProperty();
        this.descriptionProperty = new SimpleStringProperty();
        this.titleEProperty = new SimpleStringProperty();
        this.deadlineEProperty = new SimpleStringProperty();
        this.deadlineProperty = new SimpleObjectProperty<>();
        managers = new EmployeeList();
        assignedManagers = new EmployeeList();
        this.validator = new Validator();
        assignedEmployees = new EmployeeList();
        originalAssignedEmployees = new EmployeeList();
        headline = new SimpleStringProperty();
    }
    
    public void load()
    {
        super.load();
        user.setValue(model.getUser());
        if (user.get().getRole().equals(EmployeeRole.PROJECT_MANAGER))
        {
            headline.setValue("ASSIGN WORKERS");
            employeesOfManager = model.getEmployeesAssignedToManager(user.get().getWorkingNumber());
        }
        else if (user.get().getRole().equals(EmployeeRole.MAIN_MANAGER))
        {
            employeesOfManager = model.getAllProjectManagers();
        }
        titleProperty.setValue(viewState.getProject().getName());
        deadlineProperty.setValue(viewState.getProject().getDeadline());
        descriptionProperty.setValue(viewState.getProject().getDescription());
        originalAssignedEmployees = model.getAllEmployeesAssignedToProject(viewState.getProject().getId());
        assignedEmployees = model.getAllEmployeesAssignedToProject(viewState.getProject().getId());
        assignedManagers = model.getAllEmployeesAssignedToProject(viewState.getProject().getId());
        managers = model.getAllProjectManagers();
    }
    
    public void reset()
    {
        super.reset();
        titleProperty.setValue("");
        titleEProperty.setValue("");
        descriptionProperty.setValue("");
        deadlineProperty.setValue(null);
        deadlineEProperty.setValue("");
        load();
    }
    
    public boolean saveProject()
    {
        Project project1 = viewState.getProject();
        Boolean valid = true;
        try
        {
            validator.validateTitle(titleProperty.getValue());
        }
        catch (Exception e)
        {
            valid = false;
            titleEProperty.setValue(e.getMessage());
        }
        if (valid)
        {
            Project project2 = new Project(project1.getId(), titleProperty.getValue(), descriptionProperty.getValue(), deadlineProperty.getValue());
            model.updateProject(project2);
        }
        ArrayList<Integer> addedEmployees = new ArrayList<>();
        ArrayList<Integer> removedEmployees = new ArrayList<>();
        if (assignedEmployees.size() != 0)
        {
            for (int i = 0; i < assignedEmployees.size(); i++)
            {
                if (!originalAssignedEmployees.containsByWorkingNumber(assignedEmployees.get(i).getWorkingNumber()))
                {
                    addedEmployees.add(assignedEmployees.get(i).getWorkingNumber());
                }
            }
            for (int i = 0; i < originalAssignedEmployees.size(); i++)
            {
                if (!assignedEmployees.containsByWorkingNumber(originalAssignedEmployees.get(i).getWorkingNumber()))
                {
                    removedEmployees.add(originalAssignedEmployees.get(i).getWorkingNumber());
                }
            }
        }
        else
        {
            for (int i = 0; i < originalAssignedEmployees.size(); i++)
            {
                removedEmployees.add(originalAssignedEmployees.get(i).getWorkingNumber());
            }
        }
        if (addedEmployees.size() != 0)
        {
            model.assignEmployeesToProject(addedEmployees, viewState.getProject().getId());
        }
        if (removedEmployees.size() != 0)
        {
            model.dismissEmployeesFromProject(removedEmployees, viewState.getProject().getId());
        }
        return valid;
    }
    
    public void switchWorker(Employee employee)
    {
        if (assignedEmployees.containsByWorkingNumber(employee.getWorkingNumber()))
        {
            assignedEmployees.removeByWorkingNumber(employee.getWorkingNumber());
        }
        else
        {
            assignedEmployees.addEmployee(employee);
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

    public EmployeeList getEmployeesOfManager()
    {
        return employeesOfManager;
    }
    public Employee getUser()
    {
        return user.get();
    }
    public StringProperty headlineProperty()
    {
        return headline;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }

}
