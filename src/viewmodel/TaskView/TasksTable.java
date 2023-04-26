package viewmodel.TaskView;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class TasksTable
{
    private StringProperty title;
    private ObjectProperty<LocalDate> deadline;
    private StringProperty priority;
    private StringProperty status;
    private StringProperty tags;
    
    public TasksTable(String title, LocalDate deadline, String priority, String status, String tags)
    {
    this.title = new SimpleStringProperty(title);
    this.deadline = new SimpleObjectProperty<>();
    this.priority = new SimpleStringProperty(priority);
    this.status = new SimpleStringProperty(status);
    this.tags = new SimpleStringProperty(tags);
    }
    
    public String getTitle()
    {
        return title.get();
    }
    
    public StringProperty titleProperty()
    {
        return title;
    }
    
    public LocalDate getDeadline()
    {
        return deadline.get();
    }
    
    public ObjectProperty<LocalDate> deadlineProperty()
    {
        return deadline;
    }
    
    public String getPriority()
    {
        return priority.get();
    }
    
    public StringProperty priorityProperty()
    {
        return priority;
    }
    
    public String getStatus()
    {
        return status.get();
    }
    
    public StringProperty statusProperty()
    {
        return status;
    }
    
    public String getTags()
    {
        return tags.get();
    }
    
    public StringProperty tagsProperty()
    {
        return tags;
    }
}
