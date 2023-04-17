package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.Model;
import model.Task;
import model.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

public class TasksViewModel implements ViewModel
{
    private Model model;
    private StringProperty projectNameProperty;
    private StringProperty taskNameProperty;
    private StringProperty taskDescriptionProperty;
    
    
    
    private ArrayList<Employee> employees;
    private TaskList taskList;
    private ObservableList<Task> tasks;
    private StringProperty error;
    
    public TasksViewModel(Model model)
    {
       this.error = new SimpleStringProperty();
       this.model = model;
       this.tasks = FXCollections.observableArrayList();
       this.taskList = new TaskList();
    }
    
    public StringProperty getError()
    {
        return error;
    }
    
    public TaskList getTaskList()
    {
        return taskList;
    }
    
    public ObservableList<Task> getTasks()
    {
        return tasks;
    }
}
