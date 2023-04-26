package viewmodel.TaskView;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import model.Employee;

import java.time.LocalDate;

public class WorkersTable
{
    private IntegerProperty number;
    private StringProperty name;
    private StringProperty position;
    
    public WorkersTable(int number, String name)
    {
        this.number = new SimpleIntegerProperty(number);
        this.name = new SimpleStringProperty(name);
        //this.position = new SimpleStringProperty(position);
    }
    public WorkersTable(Employee employee){
        this.number = new SimpleIntegerProperty(employee.getWorkingNumber());
        this.name = new SimpleStringProperty(employee.getName());
        //this.position = new SimpleStringProperty(employee.getPosition());
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
