package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class AssignWorkersToTaskViewModel implements ViewModel {
    private ViewState viewState;
    private Model model;
    private StringProperty projectName;

    private ObjectProperty<Employee> employee;
    private ObjectProperty<Image> avatarPic;
    private EmployeeList employeesOfManager;
    private EmployeeList employeesOfTask;
    private EmployeeList employeesOfProject;
    private ObjectProperty<Employee> user;
    private StringProperty userName;
    private StringProperty userNumber;

    public AssignWorkersToTaskViewModel(Model model, ViewState viewState) {
        this.viewState = viewState;
        this.model = model;
        this.employee=new SimpleObjectProperty<>();
        this.avatarPic=new SimpleObjectProperty<>();
        employeesOfTask = new EmployeeList();
        employeesOfManager = new EmployeeList();
        projectName = new SimpleStringProperty();
        user= new SimpleObjectProperty<>();
        userName = new SimpleStringProperty();
        userNumber = new SimpleStringProperty();
    }

    public void load() {
        employee.setValue(model.getUser());
        setAvatarPicture();
        user.set(model.getUser());
        Project project = viewState.getProject();
        projectName.set(project.getName());
        employeesOfManager = model.getEmployeesAssignedToManager(user.get().getWorkingNumber());
        employeesOfProject = model.getAllEmployeesAssignedToProject(viewState.getProject().getId());
        for(int i=0;i<employeesOfManager.size();i++)
        {
            if(employeesOfProject.containsByWorkingNumber(employeesOfManager.get(i).getWorkingNumber()))
            {
                employeesOfTask.addEmployee(employeesOfManager.get(i));
            }
        }
        userName.set(user.get().getName());
        userNumber.set(user.get().getWorkingNumber().toString());
    }

    public boolean isAssigned(Employee employee) {
        if (model.getEmployeesOfTask(viewState.getTask().getId()).containsByWorkingNumber(employee.getWorkingNumber())) {
            return true;
        }
        return false;
    }

    public void assignEmployee(Employee employee) {
        if (!employeesOfTask.containsByWorkingNumber(employee.getWorkingNumber())) {
            model.assignWorkerToTask(employee.getWorkingNumber(), viewState.getTask().getId());
            employeesOfTask.addEmployee(employee);
        }
        else {
          model.removeWorkerFromTask(employee.getWorkingNumber(), viewState.getTask().getId());
          employeesOfTask.removeByWorkingNumber(employee.getWorkingNumber());
        }
    }

    public StringProperty getProjectName() {
        return projectName;
    }

    public EmployeeList getEmployeesOfManager() {
        return employeesOfManager;
    }

    public EmployeeList getEmployeesOfTask() {
        return employeesOfTask;
    }
    public String getUserName()
    {
        return userName.get();
    }
    public StringProperty userNameProperty()
    {
        return userName;
    }
    public String getUserNumber()
    {
        return userNumber.get();
    }
    public StringProperty userNumberProperty()
    {
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
