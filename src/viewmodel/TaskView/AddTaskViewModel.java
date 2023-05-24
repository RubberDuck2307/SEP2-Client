package viewmodel.TaskView;

import javafx.beans.property.*;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.*;
import util.Validator;
import viewmodel.ViewModel;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class AddTaskViewModel extends ViewModelWithNavigationMenu
{
    private ViewState viewState;
    private StringProperty nameOfTheProject;
    private StringProperty title;
    private StringProperty errorTitleMessage;
    private StringProperty errorPriorityMessage;
    private StringProperty errorTitleHours;
    private StringProperty errorDeadlineMessage;
    private ObjectProperty<LocalDate> deadline;
    private StringProperty description;
    private ObjectProperty<String> priority;
    private StringProperty estimatedHours;
    private StringProperty tags;
    private TagList tagList;
    private TagList tagListForTask;
    private ObjectProperty<javafx.scene.paint.Color> color;
    private StringProperty tagsE;

    private int estimatedHoursInt;
    private Validator validator;
    private EmployeeList workers;
    private EmployeeList employeesOfManager;
    private EmployeeList employeesOfProject;
    private ArrayList<Integer> assignedEmployeeWorkingNumbers;

    
    public AddTaskViewModel(Model model, ViewState viewState)
    {
        super(model);

        this.viewState = viewState;
        this.nameOfTheProject = new SimpleStringProperty("");
        this.title = new SimpleStringProperty("");
        this.errorTitleMessage = new SimpleStringProperty("");
        this.deadline = new SimpleObjectProperty<>(LocalDate.now());
        this.description = new SimpleStringProperty("");
        this.priority = new SimpleObjectProperty<>(null);
        this.estimatedHours = new SimpleStringProperty("");
        this.tags = new SimpleStringProperty("");
        this.tagListForTask=new TagList();
        this.color= new SimpleObjectProperty<>(Color.RED);
        this.tagsE= new SimpleStringProperty("");
        this.errorTitleHours = new SimpleStringProperty("");
        this.errorDeadlineMessage = new SimpleStringProperty("");
        this.errorPriorityMessage = new SimpleStringProperty("");
        this.validator = new Validator();
        this.workers = new EmployeeList();
        this.assignedEmployeeWorkingNumbers = new ArrayList<>();
        this.notification = new SimpleBooleanProperty(false);
    }
    public void load()
    {
        super.load();
        this.tagList= model.getAllTags();
        Project project = viewState.getProject();
        nameOfTheProject.setValue(project.getName());
        deadline.setValue(project.getDeadline());
        employeesOfManager = model.getEmployeesAssignedToManager(model.getUser().getWorkingNumber());
        employeesOfProject = model.getAllEmployeesAssignedToProject(viewState.getProject().getId());
        workers = new EmployeeList();
        for(int i=0;i<employeesOfManager.size();i++)
        {
            if(employeesOfProject.containsByWorkingNumber(employeesOfManager.get(i).getWorkingNumber()))
            {
                workers.addEmployee(model.getEmployeeByWorkingNumber(employeesOfManager.get(i).getWorkingNumber()));
            }
        }
        assignedEmployeeWorkingNumbers.clear();

    }

    public void reset(){
        super.reset();
        errorLabelsReset();
        title.setValue("");
        priority.setValue(null);
        description.setValue("");
        estimatedHours.setValue("");
        tagsE.setValue("");
        load();
    }

    public void errorLabelsReset(){
        errorTitleHours.setValue("");
        errorDeadlineMessage.setValue("");
        errorTitleMessage.setValue("");
        errorPriorityMessage.setValue("");
    }
    public StringProperty getNameOfTheProject()
    {
        return nameOfTheProject;
    }

    public StringProperty tagsEProperty()
    {
        return tagsE;
    }

    public boolean addTag(){
        boolean valid = true;
        Tag tag=new Tag(tags.getValue() ,color.getValue().toString().replace("0x", "#"));
        try
        {
            validator.validateTag(tag);
        }
        catch (Exception e)
        {
            tagsE.setValue(e.getMessage());
            valid=false;
        }
        if(valid){
            Long id=model.saveTag(tag);
            tag.setId(id);
            tagList.addTag(tag);
        }
        return valid;
    }

    public boolean add()
    {
        errorTitleHours.setValue(null);
        errorDeadlineMessage.setValue(null);
        errorTitleMessage.setValue(null);
        errorPriorityMessage.setValue(null);
        Project project = viewState.getProject();
        boolean valid = true;
        try
        {
            validator.validateTitle(title.getValue());
        }
        catch (Exception e)
        {
            valid = false;
            errorTitleMessage.setValue("Title can not be empty.");
        }
        try
        {
            validator.validateEstimatedTimer(getEstimatedHours());
        }
        catch (Exception e)
        {
            valid = false;
            errorTitleHours.setValue(e.getMessage());
        }
        if (deadline.getValue() != null)
        {
            try
            {
                //System.out.println("this is current deadline: " + deadline.getValue());
                validator.validateTaskDeadline(deadline.getValue(), project.getDeadline());
            }
            catch (Exception e)
            {
                valid = false;
                errorDeadlineMessage.setValue(e.getMessage());
            }
        }
        if (priority.getValue() == null)
        {
            valid = false;
            errorPriorityMessage.setValue("The priority needs to be set.");
        }
        if (valid)
        {
            estimatedHoursInt = Integer.parseInt(estimatedHours.getValue());
            Task task2 = new Task(title.getValue(), description.getValue(), deadline.getValue(), estimatedHoursInt, priority.getValue(), "TO DO", project.getId());
            //System.out.println("Bobek: " + task2.toString());
            Long id = model.saveTask(task2);
            model.assignEmployeesToTask(assignedEmployeeWorkingNumbers, id.longValue());
            for(int i=0;i<tagListForTask.size();i++)
            {
                model.addTagToTask(id.longValue(), tagListForTask.get(i).getId());
            }
        }
        return valid;
    }



    public StringProperty titleProperty()
    {
        return title;
    }
    
    public StringProperty errorPriorityMessageProperty()
    {
        return errorPriorityMessage;
    }
    
    public StringProperty errorDeadlineMessageProperty()
    {
        return errorDeadlineMessage;
    }
    
    public StringProperty errorTitleMessageProperty()
    {
        return errorTitleMessage;
    }
    
    public ObjectProperty<LocalDate> deadlineProperty()
    {
        return deadline;
    }
    
    public StringProperty descriptionProperty()
    {
        return description;
    }
    
    public ObjectProperty priorityProperty()
    {
        return priority;
    }
    
    public StringProperty estimatedHoursProperty()
    {
        return estimatedHours;
    }
    
    public StringProperty tagsProperty()
    {
        return tags;
    }

    public TagList getTagList()
    {
        return tagList;
    }

    public Property<Color> colorProperty()
    {
        return color;
    }

    public String getEstimatedHours()
    {
        return estimatedHours.get();
    }
    
    public StringProperty errorTitleHoursProperty()
    {
        return errorTitleHours;
    }

    public void switchTag(Tag tag){
        if (!tagListForTask.containsById(tag))
        {
            tagListForTask.addTag(tag);
        }
        else
        {
            tagListForTask.removeTag(tag);
        }
    }

    public void assignWorker(Employee employee)
    {
        if (!assignedEmployeeWorkingNumbers.contains(employee.getWorkingNumber()))
        {
            assignedEmployeeWorkingNumbers.add(employee.getWorkingNumber());
        }
        else
        {
            assignedEmployeeWorkingNumbers.remove(employee.getWorkingNumber());
        }
    }
    public EmployeeList getWorkers()
    {
        return workers;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }
}
