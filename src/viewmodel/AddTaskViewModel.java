package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import model.*;
import util.Validator;
import viewmodel.TaskView.TasksTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class AddTaskViewModel implements ViewModel
{
    private ObjectProperty<Employee> employee;
    private ObjectProperty<Image> avatarPic;
    private Model model;
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
    private int estimatedHoursInt;
    private Validator validator;
    private EmployeeList workers;
    private ArrayList<Integer> assignedEmployeeIDs;
    private StringProperty name;
    private StringProperty workingNumber;
    
    public AddTaskViewModel(Model model, ViewState viewState)
    {
        this.employee=new SimpleObjectProperty<>();
        this.avatarPic=new SimpleObjectProperty<>();
        this.model = model;
        this.viewState = viewState;
        this.nameOfTheProject = new SimpleStringProperty("");
        this.title = new SimpleStringProperty("");
        this.errorTitleMessage = new SimpleStringProperty("");
        this.deadline = new SimpleObjectProperty<>(LocalDate.now());
        this.description = new SimpleStringProperty("");
        this.priority = new SimpleObjectProperty<>(null);
        this.estimatedHours = new SimpleStringProperty("");
        this.tags = new SimpleStringProperty("");
        this.errorTitleHours = new SimpleStringProperty("");
        this.errorDeadlineMessage = new SimpleStringProperty("");
        this.errorPriorityMessage = new SimpleStringProperty("");
        this.validator = new Validator();
        this.workers = new EmployeeList();
        this.assignedEmployeeIDs = new ArrayList<>();
        this.name = new SimpleStringProperty("");
        this.workingNumber = new SimpleStringProperty("");
    }
    public void load()
    {
        employee.setValue(model.getUser());
        setAvatarPicture();
        Project project = viewState.getProject();
        nameOfTheProject.setValue(project.getName());
        deadline.setValue(project.getDeadline());
        workers = model.getEmployeesAssignedToManager(4);
        assignedEmployeeIDs.clear();
        name.setValue(model.getUser().getName());
        workingNumber.setValue(model.getUser().getWorkingNumber().toString());
    }

    public void reset(){
        errorTitleHours.setValue("");
        errorDeadlineMessage.setValue("");
        errorTitleMessage.setValue("");
        errorPriorityMessage.setValue("");
        title.setValue("");
        priority.setValue(null);
        description.setValue("");
        estimatedHours.setValue("");
        load();

    }
    public StringProperty getNameOfTheProject()
    {
        return nameOfTheProject;
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
                System.out.println("this is current deadline: " + deadline.getValue());
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
            //TODO validate priority!!
            Task task2 = new Task(title.getValue(), description.getValue(), deadline.getValue(), estimatedHoursInt, priority.getValue(), "TO DO", project.getId(), LocalDate.now());
            System.out.println("Bobek: " + task2.toString());
            Long id = model.saveTask(task2);
            model.assignEmployeesToTask(assignedEmployeeIDs, id.longValue());
        }
        return valid;
    }
    
    public StringProperty nameOfTheProjectProperty()
    {
        return nameOfTheProject;
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
    
    public String getEstimatedHours()
    {
        return estimatedHours.get();
    }
    
    public StringProperty errorTitleHoursProperty()
    {
        return errorTitleHours;
    }
    
    public void assignWorker(Employee employee)
    {
        assignedEmployeeIDs.add(employee.getWorkingNumber());
    }
    public EmployeeList getWorkers()
    {
        return workers;
    }
    
    public String getName()
    {
        return name.get();
    }
    public StringProperty nameProperty()
    {
        return name;
    }
    public String getWorkingNumber()
    {
        return workingNumber.get();
    }
    public StringProperty workingNumberProperty()
    {
        return workingNumber;
    }
    public ObjectProperty<Image> avatarPicProperty()
    {
        return avatarPic;
    }
    public boolean isWoman(){
        return Objects.equals(employee.getValue().getGender(), "F");
    }
    public void setAvatarPicture(){
        if(isWoman()){
            avatarPic.setValue(new Image("/icons/woman-avatar.png"));
        }
        else{
            avatarPic.setValue(new Image("/icons/man-avatar.png"));
        }
    }
}
