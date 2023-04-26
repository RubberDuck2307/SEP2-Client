package viewmodel;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.Model;
import model.Project;
import model.Task;
import model.TaskList;
import viewmodel.TaskView.CommentsTable;
import viewmodel.TaskView.TasksTable;
import viewmodel.TaskView.WorkersTable;

public class TasksViewModel implements ViewModel
{
    private Model model;
    private TaskList taskList;
    private StringProperty error;
    private StringProperty projectName;
    private StringProperty taskName;
    private StringProperty taskDescription;
    private ObservableList<Task> tasks;
    private ObservableList<TasksTable> tasksTables;
    private ObservableList<CommentsTable> commentsTables;
    private ObservableList<WorkersTable> workersTables;
    private ViewState viewState;
    
    public TasksViewModel(Model model)
    {
       this.error = new SimpleStringProperty();
       this.model = model;
       this.tasks = FXCollections.observableArrayList();
       this.viewState=new ViewState();
       this.taskList = new TaskList();
       taskList.addTask(viewState.getTask());
       this.projectName= new SimpleStringProperty();
       this.taskName=new SimpleStringProperty();
       this.taskDescription =new SimpleStringProperty();
       this.tasksTables=FXCollections.observableArrayList();
       this.commentsTables=FXCollections.observableArrayList();
       this.workersTables=FXCollections.observableArrayList();
       load();
    }

    public void load(){
        Project project=viewState.getProject();
        taskList=model.getAllTasksOfProject(project.getId());
        for(int i=0;i<taskList.size();i++){
            tasksTables.add(new TasksTable(taskList.getTask(i)));
        }
    }



    public ObservableList<TasksTable> getAll() {
        return tasksTables;
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
