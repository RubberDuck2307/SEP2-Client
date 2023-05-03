package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import model.Employee;


public class WorkersWithCheckboxTable
{
    private IntegerProperty number;
    private StringProperty name;
    private StringProperty position;
    private CheckBox checkbox;

    public WorkersWithCheckboxTable(int number, String name)
    {
        this.number = new SimpleIntegerProperty(number);
        this.name = new SimpleStringProperty(name);
        //this.position = new SimpleStringProperty(position);
    }
    public WorkersWithCheckboxTable(Employee employee){
        this.number = new SimpleIntegerProperty(employee.getWorkingNumber());
        this.name = new SimpleStringProperty(employee.getName());
        //this.position = new SimpleStringProperty(employee.getPosition());
    }

    public CheckBox getCheckbox()
    {
        return checkbox;
    }

    public void setCheckbox(CheckBox checkbox)
    {
        this.checkbox = checkbox;
    }

    public int getNumber()
    {
        return number.get();
    }
    
    public IntegerProperty numberProperty()
    {
        return number;
    }
    
    public String getName()
    {
        return name.get();
    }
    
    public StringProperty nameProperty()
    {
        return name;
    }
    
    public String getPosition()
    {
        return position.get();
    }
    
    public StringProperty positionProperty()
    {
        return position;
    }

    public ObservableValue<String> getNumberProperty()
    {
        return number.asString();
    }

    public ObservableValue<String> getNameProperty()
    {
        return name;
    }
    /*public ObservableValue<String> getPositionProperty()
    {
        return position;
    }*/
}
