package viewmodel.EmployeeView;

import javafx.beans.property.*;
import javafx.scene.image.Image;
import model.Employee;
import model.Model;
import viewmodel.ViewModel;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class HomeViewModel implements ViewModel, PropertyChangeListener
{
    private Model model;
    private ViewState viewState;
    private ObjectProperty<Employee> employeeProperty;
    private ObjectProperty<Image> avatarPic;
    private StringProperty name, workingNumber;
    private BooleanProperty notification;
    
    public HomeViewModel(Model model, ViewState viewState)
    {
        this.model = model;
        this.viewState = viewState;
        this.name = new SimpleStringProperty();
        this.workingNumber = new SimpleStringProperty();
        employeeProperty = new SimpleObjectProperty<>();
        this.avatarPic=new SimpleObjectProperty<>();
        notification = new SimpleBooleanProperty();
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("notification")){
            notification.setValue(true);
        }
    }

}
