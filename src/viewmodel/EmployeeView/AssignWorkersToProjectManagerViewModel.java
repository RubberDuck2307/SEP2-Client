package viewmodel.EmployeeView;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import model.Employee;
import model.EmployeeList;
import model.Model;
import viewmodel.ViewModel;
import viewmodel.ViewState;

import java.util.Objects;

public class AssignWorkersToProjectManagerViewModel implements ViewModel
{
    private ViewState viewState;
    private Model model;
    private StringProperty employeeName;

    private ObjectProperty<Employee> connectedEmployee;
    private ObjectProperty<Image> avatarPic;
    private EmployeeList employeesOfManager;
    private EmployeeList employees;
    private ObjectProperty<Employee> user;
    private StringProperty userName;
    private StringProperty userNumber;

    public AssignWorkersToProjectManagerViewModel(Model model, ViewState viewState) {
        this.viewState = viewState;
        this.model = model;
        this.connectedEmployee =new SimpleObjectProperty<>();
        this.avatarPic=new SimpleObjectProperty<>();
        employeesOfManager = new EmployeeList();
        employees = new EmployeeList();
        employeeName = new SimpleStringProperty();
        user= new SimpleObjectProperty<>();
        userName = new SimpleStringProperty();
        userNumber = new SimpleStringProperty();
    }

    public void load() {
        employeesOfManager=model.getEmployeesAssignedToManager(viewState.getEmployee().getWorkingNumber());
        employees = model.getAllWorkers();
    }

    public void reset(){
        connectedEmployee.setValue(model.getUser());
        setAvatarPicture();
        employeeName.set(viewState.getEmployee().getName());
        user.set(model.getUser());
        //System.out.println(model.getUser().toString());
        userName.set(user.get().getName());
        userNumber.set(user.get().getWorkingNumber().toString());
        load();
    }
    public boolean isAssigned(Employee employee) {
        if (employeesOfManager.containsByWorkingNumber(employee.getWorkingNumber())) {
            return true;
        }
        return false;
    }

    public void assignEmployee(Employee employee) {
        if (!employeesOfManager.containsByWorkingNumber(employee.getWorkingNumber())) {
            model.assignWorkerToManager(viewState.getEmployee().getWorkingNumber(), employee.getWorkingNumber());
            employeesOfManager.addEmployee(employee);
        }
        else {
          model.removeWorkerFromManager(viewState.getEmployee().getWorkingNumber(), employee.getWorkingNumber());
          employeesOfManager.removeByWorkingNumber(employee.getWorkingNumber());
        }
    }

    public StringProperty getEmployeeName() {
        return employeeName;
    }

    public EmployeeList getEmployeesOfManager() {
        return employeesOfManager;
    }

    public EmployeeList getEmployees()
    {
        return employees;
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
        return Objects.equals(connectedEmployee.getValue().getGender(), "F");
    }
    public void setAvatarPicture(){
        if(isWoman()){
            avatarPic.setValue(new Image("/icons/woman-avatar.png"));
        }
        else{
            avatarPic.setValue(new Image("/icons/man-avatar.png"));
        }
    }
    public Employee getEmployeeProperty() {
        return user.get();
    }

}
