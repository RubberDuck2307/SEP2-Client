package viewmodel.TaskView;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import model.Task;

import java.time.LocalDate;

public class TasksTable
{
    private StringProperty title;
    private StringProperty deadline;
    private StringProperty priority;
    private StringProperty status;
    private StringProperty estimatedTime;
    private Button button;
    private Button statusButton;
    private Long id;
    private StringProperty tags;
    private Button btton;



    public TasksTable(Task task)
    {
        this.title = new SimpleStringProperty(task.getName());
        setDeadline(task.getDeadline());
        this.priority = new SimpleStringProperty(task.getPriority());
        this.status = new SimpleStringProperty(task.getStatus());
        //System.out.println("");
        setEstimatedTime(task.getEstimatedTime());
        this.id=task.getId();
    }

    public Button getButton()
    {
        return button;
    }

    public void setButton(Button button)
    {
        this.button = button;
    }

    public Button getBtton()
    {
        return btton;
    }
    public void setBtton(Button btton)
    {
        this.btton = btton;
    }
    public void setDeadline(LocalDate deadline)
    {
        if(deadline!=null)    this.deadline = new SimpleStringProperty(deadline.toString());
        else this.deadline=new SimpleStringProperty("");
    }
    public void setEstimatedTime(int estimatedTime)
    {
        if(estimatedTime!=0)    this.estimatedTime = new SimpleStringProperty(estimatedTime + "");
        else this.estimatedTime=new SimpleStringProperty("not set");
    }

    public String getTitle()
    {
        return title.get();
    }
    
    public StringProperty titleProperty()
    {
        return title;
    }
    
    public String getDeadline()
    {
        return deadline.get();
    }

    public String getEstimatedTime()
    {
        return estimatedTime.get();
    }

    public StringProperty estimatedTimeProperty()
    {
        return estimatedTime;
    }

    public Long getId()
    {
        return id;
    }

    public StringProperty deadlineProperty()
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

    public Button getStatusButton() {
        return statusButton;
    }

    public void setStatusButton(Button statusButton) {
        this.statusButton = statusButton;
    }

    public ObservableValue<String> getTitleProperty()
    {
        return title;
    }
    public ObservableValue<String> getDeadlineProperty()
    {
        return deadline;
    }
    public ObservableValue<String> getPriorityProperty()
    {
        return priority;
    }
    public ObservableValue<String> getEstimatedTimeProperty()
    {
        return estimatedTime;
    }
    public ObservableValue<String> getStatusProperty()
    {
        return status;
    }
}
