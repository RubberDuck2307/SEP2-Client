package viewmodel.TaskView;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class WorkersTable
{
    private StringProperty number;
    private StringProperty name;
    private StringProperty position;
    
    public WorkersTable(String number, String name, String position)
    {
        this.number = new SimpleStringProperty(number);
        this.name = new SimpleStringProperty(name);
        this.position = new SimpleStringProperty(position);
    }
    
    public String getNumber()
    {
        return number.get();
    }
    
    public StringProperty numberProperty()
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
}
