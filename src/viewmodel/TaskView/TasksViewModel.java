package viewmodel.TaskView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import model.*;
import viewmodel.TaskView.CommentsTable;
import viewmodel.TaskView.TasksTable;
import viewmodel.TaskView.WorkersTable;
import viewmodel.ViewModel;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class TasksViewModel extends ViewModelWithNavigationMenu
{
    private TaskList taskList;
    private StringProperty error;
    private StringProperty projectName;
    private StringProperty estimatedTime;
    private StringProperty taskName;
    private StringProperty taskDescription;
    private ObservableList<Task> tasks;
    private ObservableList<TasksTable> tasksTables;
    private TagList tagList;
    private ObservableList<WorkersTable> workersTables;
    private StringProperty name;
    private IntegerProperty number;
    private ViewState viewState;
    private BooleanProperty isTaskSelected;


    public TasksViewModel(Model model, ViewState viewState)
    {
        super(model);
        this.error = new SimpleStringProperty();
        this.tasks = FXCollections.observableArrayList();
        this.viewState = viewState;
        this.taskList = new TaskList();
        this.estimatedTime = new SimpleStringProperty();
        //employeeList.addEmployee(viewState.getEmployee());
        this.projectName = new SimpleStringProperty();
        this.taskName = new SimpleStringProperty();
        this.taskDescription = new SimpleStringProperty();
        this.tasksTables = FXCollections.observableArrayList();
        this.workersTables = FXCollections.observableArrayList();
        this.name = new SimpleStringProperty();
        this.number = new SimpleIntegerProperty();
        this.isTaskSelected = new SimpleBooleanProperty();

    }
    public void reset()
    {
        super.reset();
        isTaskSelected.set(false);
        load();
    }

    public void load()
    {
        super.load();
        Project project = viewState.getProject();
        taskList = model.getAllTasksOfProject(project.getId());
        taskName.setValue("Description");
        projectName.setValue(project.getName());
        taskDescription.setValue("Select a task to see the description and comments");
        tasksTables.clear();
        tasks.clear();
        workersTables.clear();
        for (int i = 0; i < taskList.size(); i++)
        {
            tasksTables.add(new TasksTable(taskList.getTask(i)));
            tasks.add(taskList.getTask(i));
        }
    }

    public boolean changeStatus(String status, Task task)
    {
        if (model.getUser().getRole().equals(EmployeeRole.WORKER))
        {
            boolean contains = false;
            for (int i = 0; i < task.getWorkers().size(); i++)
            {
                if (task.getWorkers().get(i).getWorkingNumber().equals(model.getUser().getWorkingNumber()))
                {
                    contains = true;
                }
            }
            if (!contains)
            {
                return false;
            }
        }
        else if (!model.getUser().getRole().equals(EmployeeRole.PROJECT_MANAGER))
        {
            return false;
        }
        try
        {
            model.changeTaskStatus(task.getId(), status);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }



    public ObservableList<WorkersTable> getWorkersTables()
    {
        return workersTables;
    }
    public StringProperty getError()
    {
        return error;
    }

    public StringProperty projectNameProperty()
    {
        return projectName;
    }

    public StringProperty taskNameProperty()
    {
        return taskName;
    }

    public StringProperty taskDescriptionProperty()
    {
        return taskDescription;
    }

    public String getName()
    {
        return name.get();
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public int getNumber()
    {
        return number.get();
    }



    public ObservableList<Task> getTasks()
    {
        return tasks;
    }

    public void chooseTask(Long id)
    {
        Task task = taskList.getTaskById(id);
        taskName.setValue(task.getName());
        taskDescription.setValue(task.getDescription());
        viewState.setTask(task);
        EmployeeList employeeList = model.getEmployeesOfTask(id);
        tagList = model.getTagsOfTask(id);
        workersTables.clear();
        for (int i = 0; i < employeeList.size(); i++)
        {
            workersTables.add(new WorkersTable(employeeList.get(i)));
        }
        isTaskSelected.set(true);
    }


    public BooleanProperty isTaskSelectedProperty()
    {
        return isTaskSelected;
    }

    public TagList getTagList()
    {
        return tagList;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }
    public void delete(Task task)
    {
        model.deleteTaskById(task.getId());
    }
}
