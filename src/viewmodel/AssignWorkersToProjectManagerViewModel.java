package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import model.Employee;
import model.EmployeeList;
import model.Model;
import model.Project;

import java.util.Objects;

public class AssignWorkersToProjectManagerViewModel implements ViewModel {
    private ViewState viewState;
    private Model model;
    private StringProperty employeeName;

    private ObjectProperty<Employee> employee;
    private ObjectProperty<Image> avatarPic;
    private EmployeeList employeesOfManager;
    private ObjectProperty<Employee> user;
    private StringProperty userName;
    private StringProperty userNumber;

    public AssignWorkersToProjectManagerViewModel(Model model, ViewState viewState) {
        this.viewState = viewState;
        this.model = model;
        this.employee=new SimpleObjectProperty<>();
        this.avatarPic=new SimpleObjectProperty<>();
        employeesOfManager = new EmployeeList();
        employeeName = new SimpleStringProperty();
        user= new SimpleObjectProperty<>();
        userName = new SimpleStringProperty();
        userNumber = new SimpleStringProperty();
    }

    public void load() {

    }

    public void reset(){
        employee.setValue(model.getUser());
        setAvatarPicture();
        employeeName.set(viewState.getEmployee().getName());
        user.set(model.getUser());
        System.out.println(model.getUser().toString());
        employeesOfManager = model.getEmployeesAssignedToManager(user.get().getWorkingNumber());
        userName.set(user.get().getName());
        userNumber.set(user.get().getWorkingNumber().toString());
        load();
    }
    public boolean isAssigned(Employee employee) {
        EmployeeList assignedEmployees=model.getEmployeesOfTask(viewState.getTask().getId());
        if (assignedEmployees.containsByWorkingNumber(employee.getWorkingNumber())) {
            return true;
        }
        return false;
    }

    public void assignEmployee(Employee employee) {
        if (!employeesOfManager.containsByWorkingNumber(employee.getWorkingNumber())) {
            model.assignWorkerToManager(employee.getWorkingNumber(), viewState.getEmployee().getWorkingNumber());
            employeesOfManager.addEmployee(employee);
        }
        else {
          model.removeWorkerFromManager(employee.getWorkingNumber(), viewState.getEmployee().getWorkingNumber());
          employeesOfManager.removeByWorkingNumber(employee.getWorkingNumber());
        }
    }

    public StringProperty getEmployeeName() {
        return employeeName;
    }

    public EmployeeList getEmployeesOfManager() {
        return employeesOfManager;
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
