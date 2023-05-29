package viewmodel.TaskView;

import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.*;
import util.Validator;
import viewmodel.ViewModel;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class EditTaskViewModel extends ViewModelWithNavigationMenu

{
  private ViewState viewState;


  private StringProperty nameOfTheTask;
  private StringProperty title;
  private StringProperty errorTitleMessage;
  private StringProperty errorTitleHours;
  private ObjectProperty<LocalDate> deadline;
  private StringProperty description;
  private ObjectProperty<String> priority;
  private int estimatedHoursInt;
  private ObjectProperty<String> status;
  private IntegerProperty estimatedHours;
  private StringProperty tags;
  private TagList tagList;
  private TagList initialTags;
  private TagList assignedTags;
  private ObjectProperty<javafx.scene.paint.Color> color;
  private StringProperty tagsE;
  private StringProperty errorPriorityMessage;
  private StringProperty errorDeadlineMessage;
  private Validator validator;
  private EmployeeList employees;
  private EmployeeList employeesOfManager;
  private EmployeeList employeesOfProject;
  private EmployeeList originalAssignedEmployees;
  private EmployeeList assignedEmployees;


  public EditTaskViewModel(Model model, ViewState viewState)
  {
    super(model);
    this.viewState = viewState;
    this.nameOfTheTask = new SimpleStringProperty();
    this.title = new SimpleStringProperty();
    this.errorTitleMessage = new SimpleStringProperty();
    LocalDate localDate = LocalDate.now();
    this.deadline = new SimpleObjectProperty<>(localDate);
    this.description = new SimpleStringProperty();
    this.priority = new SimpleObjectProperty<>();
    this.estimatedHours = new SimpleIntegerProperty();
    this.tags = new SimpleStringProperty();
    this.initialTags=new TagList();
    this.assignedTags=new TagList();
    this.color= new SimpleObjectProperty<>(Color.RED);
    this.tagsE= new SimpleStringProperty("");
    this.errorTitleHours = new SimpleStringProperty();
    this.status = new SimpleObjectProperty<>();
    this.validator = new Validator();
    this.errorDeadlineMessage = new SimpleStringProperty("");
    this.errorPriorityMessage = new SimpleStringProperty("");
    employees = new EmployeeList();
    employeesOfManager = new EmployeeList();
    employeesOfProject = new EmployeeList();
    assignedEmployees = new EmployeeList();
    originalAssignedEmployees = new EmployeeList();
  }
  public void load()
  {
    super.load();
    Task task= viewState.getTask();
    Long taskID=task.getId();
    this.tagList= model.getAllTags();
    initialTags= model.getTagsOfTask(taskID);
    assignedTags= model.getTagsOfTask(taskID);

    originalAssignedEmployees = model.getEmployeesOfTask(taskID);
    assignedEmployees = model.getEmployeesOfTask(taskID);
    mandatoryEmployeeFiltering();


    nameOfTheTask.setValue(viewState.getTask().getName());
    deadline.setValue(task.getDeadline());
    status.setValue(task.getStatus());
    priority.setValue(task.getPriority());
    title.setValue(task.getName());
    description.setValue(task.getDescription());
    estimatedHours.setValue(task.getEstimatedTime());
  }

  private void mandatoryEmployeeFiltering(){
    employeesOfManager = model.getEmployeesAssignedToManager(model.getUser().getWorkingNumber());
    employeesOfProject = model.getAllEmployeesAssignedToProject(viewState.getProject().getId());
    employees = new EmployeeList();
    for(int i=0;i<employeesOfManager.size();i++)
    {
      if(employeesOfProject.containsByWorkingNumber(employeesOfManager.get(i).getWorkingNumber()))
      {
        employees.addEmployee(model.getEmployeeByWorkingNumber(employeesOfManager.get(i).getWorkingNumber()));
      }
    }
  }

  public void reset(){
    super.reset();
    errorTitleMessage.setValue("");
    errorTitleHours.setValue("");
    tagsE.setValue("");
    load();
  }

  public void setPriority(){
    Task task = viewState.getTask();
    priority.setValue(task.getPriority());
  }
  public StringProperty getNameOfTheTask()
  {
    return nameOfTheTask;
  }
  public boolean add(){
    errorTitleHours.setValue(null);
    errorDeadlineMessage.setValue(null);
    errorTitleMessage.setValue(null);
    errorPriorityMessage.setValue(null);
    Project project = viewState.getProject();
    Task task1 = viewState.getTask();
    boolean valid = true;

    if (Objects.equals(priority.getValue(), null))
    {
      priority.setValue(task1.getPriority());
    }
    if (Objects.equals(status.getValue(), null))
    {
      status.setValue(task1.getStatus());
    }
    if(deadline.getValue()==null){
      deadline.setValue(project.getDeadline());
    }
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
      validator.validateEstimatedTimer(getEstimatedHours().toString());
    }
    catch (Exception e)
    {
      valid = false;
      errorTitleHours.setValue(e.getMessage());
    }
    if(deadline.getValue()!=null){
      try
      {
        validator.validateTaskDeadline(deadline.getValue(), project.getDeadline());
      }
      catch (Exception e)
      {
        valid = false;
        errorDeadlineMessage.setValue(e.getMessage());
      }
    }
    if (valid)
    {
      Task task2 = new Task(task1.getId(), title.getValue(), description.getValue(), deadline.getValue(), estimatedHours.getValue(), priority.getValue(), status.getValue(),
          project.getId());
      model.updateTask(task2);
      assignWorkersFromTable();
      assignTagsFromTable();
    }
    return valid;

  }

  public void assignTagsFromTable(){
    ArrayList<Tag> addedTags = new ArrayList<>();
    ArrayList<Tag> removedTags = new ArrayList<>();

    if (assignedTags.size() != 0) {
      for (int i = 0; i < assignedTags.size(); i++) {

        if (!initialTags.containsById(assignedTags.get(i))) {
          addedTags.add(assignedTags.get(i));
        }
      }
      for (int i = 0; i < initialTags.size(); i++) {
        if (!assignedTags.containsById(initialTags.get(i))) {
          removedTags.add(initialTags.get(i));
        }
      }
    } else {
      for (int i = 0; i < initialTags.size(); i++) {
        removedTags.add(initialTags.get(i));
      }
    }
    Long taskID= viewState.getTask().getId();
    if (addedTags.size() != 0) {
      for(Tag tag:addedTags){
        model.addTagToTask(taskID, tag.getId());
      }
    }
    if (removedTags.size() != 0) {
      for (Tag tag:removedTags)
      {
        model.removeTagFromTask(taskID, tag.getId());
      }
    }
  }

  public void assignWorkersFromTable(){
    ArrayList<Integer> addedEmployees = new ArrayList<>();
    ArrayList<Integer> removedEmployees = new ArrayList<>();

    if (assignedEmployees.size() != 0) {
      for (int i = 0; i < assignedEmployees.size(); i++) {

        if (!originalAssignedEmployees.containsByWorkingNumber(assignedEmployees.get(i).getWorkingNumber())) {
          addedEmployees.add(assignedEmployees.get(i).getWorkingNumber());
        }
      }
      for (int i = 0; i < originalAssignedEmployees.size(); i++) {
        if (!assignedEmployees.containsByWorkingNumber(originalAssignedEmployees.get(i).getWorkingNumber())) {
          removedEmployees.add(originalAssignedEmployees.get(i).getWorkingNumber());
        }
      }
    } else {
      for (int i = 0; i < originalAssignedEmployees.size(); i++) {
        removedEmployees.add(originalAssignedEmployees.get(i).getWorkingNumber());
      }
    }

    if (addedEmployees.size() != 0) {
      model.assignEmployeesToTask(addedEmployees, viewState.getTask().getId());
    }
    if (removedEmployees.size() != 0) {
      model.unassignEmployeesFromTask(removedEmployees, viewState.getTask().getId());
    }
  }

    public void switchWorker(Employee employee) {
        if (assignedEmployees.containsByWorkingNumber(employee.getWorkingNumber())) {
            assignedEmployees.removeByWorkingNumber(employee.getWorkingNumber());
        }
        else
        {
            assignedEmployees.addEmployee(employee);
        }

    }

  public void switchTag(Tag tag){
    if (!assignedTags.containsById(tag))
    {
      assignedTags.addTag(tag);
    }
    else
    {
      assignedTags.removeTag(tag);
    }
  }

  public boolean isTagAssigned(Tag tag){
    return assignedTags.containsById(tag);
  }

    public boolean isEmployeeAssigned(Employee employee) {
        return assignedEmployees.containsByWorkingNumber(employee.getWorkingNumber());
    }

    public EmployeeList getEmployees()
    {
        return employees;
    }


    public StringProperty titleProperty()
    {
        return title;
    }

    public StringProperty errorTitleMessageProperty()
    {
        return errorTitleMessage;
    }

    public Integer getEstimatedHours()
    {
        return estimatedHours.get();
    }


    public StringProperty errorPriorityMessageProperty()
    {
        return errorPriorityMessage;
    }


    public StringProperty errorDeadlineMessageProperty()
    {
        return errorDeadlineMessage;
    }

    public ObjectProperty<String> statusProperty()
    {
        return status;
    }

    public ObjectProperty<LocalDate> deadlineProperty()
    {
        return deadline;
    }

    public StringProperty descriptionProperty()
    {
        return description;
    }

    public ObjectProperty<String> priorityProperty()
    {
        return priority;
    }

    public IntegerProperty estimatedHoursProperty()
    {
        return estimatedHours;
    }

    public StringProperty tagsProperty()
    {
        return tags;
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
  public void resetDeadline(){
    deadline.setValue(viewState.getProject().getDeadline());
  }

  public TagList getTagList()
  {
    return tagList;
  }


  public Property<Color> colorProperty()
  {
    return color;
  }

  public StringProperty errorTitleHoursProperty() {
        return errorTitleHours;
    }

    public Employee getEmployeeProperty()
    {
        return employee.get();
    }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    super.propertyChange(evt);
    }
}
