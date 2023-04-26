package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Task;
import model.TaskList;
import viewmodel.TaskView.CommentsTable;
import viewmodel.TaskView.TasksTable;
import viewmodel.TaskView.WorkersTable;

public class TasksViewModel implements ViewModel
{
    private Model model;
    private TaskList taskList;
    private ObservableList<Task> tasks;
    private StringProperty error;
    private StringProperty projectName;
    private StringProperty taskName;
    private StringProperty taskDescription;
    private ObservableList<TasksTable> tasksTables;
    private ObservableList<CommentsTable> commentsTables;
    private ObservableList<WorkersTable> workersTables;
    
    public TasksViewModel(Model model)
    {
       this.error = new SimpleStringProperty();
       this.model = model;
       this.tasks = FXCollections.observableArrayList();
       this.taskList = new TaskList();
       this.projectName= new SimpleStringProperty();
       this.taskName=new SimpleStringProperty();
       this.taskDescription =new SimpleStringProperty();


    }
    
    public StringProperty getError()
    {
        return error;
    }
    
    public String getProjectName()
    {
        return projectName.get();
    }
    
    public StringProperty projectNameProperty()
    {
        return projectName;
    }
    
    public String getTaskName()
    {
        return taskName.get();
    }
    
    public StringProperty taskNameProperty()
    {
        return taskName;
    }
    
    public String getTaskDescription()
    {
        return taskDescription.get();
    }
    
    public StringProperty taskDescriptionProperty()
    {
        return taskDescription;
    }
}
