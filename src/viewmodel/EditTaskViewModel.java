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

public class EditTaskViewModel implements ViewModel
{
  private Model model;
  private ViewState viewState;
  private ObjectProperty<Employee> employee;
  private ObjectProperty<Image> avatarPic;
  private StringProperty nameOfTheProject;
  private StringProperty title;
  private StringProperty errorTitleMessage;
  private StringProperty errorTitleHours;
  private ObjectProperty<LocalDate> deadline;
  private StringProperty description;
  private ObjectProperty<String> priority;
  private ObjectProperty<String> status;
  private IntegerProperty estimatedHours;
  private StringProperty tags;
  private int estimatedHoursInt;
  private StringProperty errorPriorityMessage;
  private StringProperty errorDeadlineMessage;
  private Validator validator;
  private EmployeeList employees;
  private EmployeeList employeesOfManager;
  private EmployeeList employeesOfProject;
  private EmployeeList originalAssignedEmployees;
  private EmployeeList assignedEmployees;
  private StringProperty name, workingNumber;


  public EditTaskViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.employee=new SimpleObjectProperty<>();
    this.avatarPic=new SimpleObjectProperty<>();
    this.nameOfTheProject = new SimpleStringProperty();
    this.title = new SimpleStringProperty();
    this.errorTitleMessage = new SimpleStringProperty();
    LocalDate localDate = LocalDate.now();
    this.deadline = new SimpleObjectProperty<>(localDate);
    this.description = new SimpleStringProperty();
    this.priority = new SimpleObjectProperty<>();
    this.estimatedHours = new SimpleIntegerProperty();
    this.tags = new SimpleStringProperty();
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
    name = new SimpleStringProperty();
    workingNumber = new SimpleStringProperty();
  }
  public void load()
  {
    employee.setValue(model.getUser());
    setAvatarPicture();
    originalAssignedEmployees = model.getEmployeesOfTask(viewState.getTask().getId());
    assignedEmployees = model.getEmployeesOfTask(viewState.getTask().getId());
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
    //employees = model.getEmployeesAssignedToManager(model.getUser().getWorkingNumber());
    Project project = viewState.getProject();

    nameOfTheProject.setValue(project.getName());
    Task task = viewState.getTask();
    deadline.setValue(task.getDeadline());
    status.setValue(task.getStatus());
    priority.setValue(task.getPriority());
    title.setValue(task.getName());
    description.setValue(task.getDescription());
    estimatedHours.setValue(task.getEstimatedTime());
    name.setValue(model.getUser().getName());
    workingNumber.setValue(model.getUser().getWorkingNumber().toString());
  }
  public void setPriority(){
    Task task = viewState.getTask();
    priority.setValue(task.getPriority());
  }
  public void reset(){
    errorTitleMessage.setValue("");
    errorTitleHours.setValue("");
    load();
  }
  public StringProperty getNameOfTheProject()
  {
    return nameOfTheProject;
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
        System.out.println("this is current deadline: " + deadline.getValue());
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
      Task task2 = new Task(task1.getId(), title.getValue(), description.getValue(), deadline.getValue(), estimatedHoursInt, priority.getValue(), status.getValue(),
          project.getId(), LocalDate.now());
      model.updateTask(task2);
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
    return valid;

  }

    public void switchWorker(Employee employee) {
        if (assignedEmployees.containsByWorkingNumber(employee.getWorkingNumber())) {
            assignedEmployees.removeByWorkingNumber(employee.getWorkingNumber());
        } else {
            assignedEmployees.addEmployee(employee);
        }

    }

    public boolean isEmployeeAssigned(Employee employee) {
        return assignedEmployees.containsByWorkingNumber(employee.getWorkingNumber());
    }

    public EmployeeList getEmployees() {
        return employees;
    }

    public StringProperty nameOfTheProjectProperty() {
        return nameOfTheProject;
    }

    public StringProperty titleProperty() {
        return title;
    }


    public StringProperty errorTitleMessageProperty() {
        return errorTitleMessage;
    }

  public Integer getEstimatedHours()
  {
    return estimatedHours.get();
  }

    public String getErrorPriorityMessage() {
        return errorPriorityMessage.get();
    }

    public StringProperty errorPriorityMessageProperty() {
        return errorPriorityMessage;
    }

    public String getErrorDeadlineMessage() {
        return errorDeadlineMessage.get();
    }

    public StringProperty errorDeadlineMessageProperty() {
        return errorDeadlineMessage;
    }

    public ObjectProperty<String> statusProperty() {
        return status;
    }

    public ObjectProperty<LocalDate> deadlineProperty() {
        return deadline;
    }


    public StringProperty descriptionProperty() {
        return description;
    }


    public ObjectProperty<String> priorityProperty() {
        return priority;
    }



  public IntegerProperty estimatedHoursProperty()
  {
    return estimatedHours;
  }

    public StringProperty tagsProperty() {
        return tags;
    }


    public StringProperty errorTitleHoursProperty() {
        return errorTitleHours;
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
  public Employee getEmployeeProperty() {
    return employee.get();
  }

}
