package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import model.Employee;
import model.Model;

import java.util.Objects;

public class HomeViewModel implements ViewModel
{
    private Model model;
    private ViewState viewState;
    private ObjectProperty<Employee> employeeProperty;
    private ObjectProperty<Image> avatarPic;
    private StringProperty name, workingNumber;
    
    public HomeViewModel(Model model, ViewState viewState)
    {
        this.model = model;
        this.viewState = viewState;
        this.name = new SimpleStringProperty();
        this.workingNumber = new SimpleStringProperty();
        employeeProperty = new SimpleObjectProperty<>();
        this.avatarPic=new SimpleObjectProperty<>();
    }

    public void reset()
    {
        load();
    }
    public void load()
    {
        employeeProperty.set(model.getUser());
        name.setValue(model.getUser().getName());
        workingNumber.setValue(model.getUser().getWorkingNumber().toString());
        setAvatarPicture();
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
    public Employee getEmployeeProperty()
    {
        return employeeProperty.get();
    }
    public ObjectProperty<Employee> employeePropertyProperty()
    {
        return employeeProperty;
    }

    public ObjectProperty<Image> avatarPicProperty()
    {
        return avatarPic;
    }
    public boolean isWoman(){
        return Objects.equals(employeeProperty.getValue().getGender(), "F");
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
