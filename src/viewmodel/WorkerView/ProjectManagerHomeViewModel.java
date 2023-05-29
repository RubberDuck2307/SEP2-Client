package viewmodel.WorkerView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import model.*;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.TaskView.TasksTable;
import viewmodel.ViewModel;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import javax.swing.text.html.ImageView;
import java.beans.PropertyChangeEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class ProjectManagerHomeViewModel extends ViewModelWithNavigationMenu
{

  private StringProperty managerName;
  private StringProperty managerRole;
  private StringProperty managerDateOfBirth;
  private StringProperty managerPhoneNumber;
  private StringProperty managerEmail;
  private StringProperty workerName2;

  private ObservableList<WorkersTable> workersTables;
  private EmployeeList employeeList;

  private ObservableList<ProjectsTable> currentProjectsTable;
  private ProjectList projectList;


  private ObservableList<NotificationTable> notificationList;

  public ProjectManagerHomeViewModel(Model model)
  {
    super(model);
    this.workersTables = FXCollections.observableArrayList();
    this.workerName2 = new SimpleStringProperty();

    this.managerName = new SimpleStringProperty();
    this.managerRole = new SimpleStringProperty();
    this.managerDateOfBirth = new SimpleStringProperty();
    this.managerPhoneNumber = new SimpleStringProperty();
    this.managerEmail = new SimpleStringProperty();

    this.currentProjectsTable = FXCollections.observableArrayList();
    this.notificationList = FXCollections.observableArrayList();
  }
  public void headline()
  {
    Employee worker = model.getUser();
    LocalTime time = LocalTime.now();
    int hour = time.getHour();
    switch (hour)
    {
      case 6, 7, 8, 9, 10, 11, 12 -> workerName2.setValue("Good morning, " + worker.getName() + "!");
      case 13, 14, 15, 16, 17 -> workerName2.setValue("Good afternoon, " + worker.getName() + "!");
      case 18, 19, 20, 21, 22, 23 -> workerName2.setValue("Good evening, " + worker.getName() + "!");
      case 0, 1, 2, 3, 4, 5 -> workerName2.setValue("Good night, " + worker.getName() + "!");
    }
  }


  public void reset(){
    super.reset();
    load();
  }
  public void load()
  {
    super.load();

    Employee employee = model.getUser();
    managerName.setValue(employee.getName());
    managerEmail.setValue(employee.getEmail());
    managerPhoneNumber.setValue(employee.getPhoneNumber());
    managerRole.setValue(employee.getRoleString());
    managerDateOfBirth.setValue(employee.getDob().toString());

    projectList = model.getAllProjectsByWorkingNumber(employee.getWorkingNumber());
    employeeList = model.getEmployeesAssignedToManager(employee.getWorkingNumber());
    workersTables.clear();
    for (int i = 0; i < employeeList.size(); i++)
    {
      workersTables.add(new viewmodel.WorkerView.WorkersTable(employeeList.get(i)));
    }
    currentProjectsTable.clear();
    for (int i = 0; i < projectList.size(); i++)
    {
      currentProjectsTable.add(new ProjectsTable(projectList.get(i)));
    }
    headline();
    getNotifications();

  }

  public void logOut() {
    model.logOut();
  }
  private void getNotifications(){
    notificationList.clear();
    IdObjectList<AssignedToProjectNotification> notifications = model.getAssignedToProjectNotification(model.getUser().getWorkingNumber());
    for (int i = notifications.size() -1; i >= 0; i--)
    {
      notificationList.add(new NotificationTable(notifications.get(i)));
    }
  }

  public void deleteNotification(String message){
  }
  public ObservableList<viewmodel.WorkerView.WorkersTable> getWorkersTable(){return workersTables;}
  public ObservableList<ProjectsTable> getCurrentProjectsTableTable(){return currentProjectsTable;}



  public String getManagerName()
  {
    return managerName.get();
  }

  public String getWorkerName2()
  {
    return workerName2.get();
  }

  public StringProperty workerName2Property()
  {
    return workerName2;
  }

  public StringProperty managerNameProperty()
  {
    return managerName;
  }

  public String getManagerRole()
  {
    return managerRole.get();
  }

  public StringProperty managerRoleProperty()
  {
    return managerRole;
  }

  public String getManagerDateOfBirth()
  {
    return managerDateOfBirth.get();
  }

  public StringProperty managerDateOfBirthProperty()
  {
    return managerDateOfBirth;
  }

  public String getManagerPhoneNumber()
  {
    return managerPhoneNumber.get();
  }

  public StringProperty managerPhoneNumberProperty()
  {
    return managerPhoneNumber;
  }

  public String getManagerEmail()
  {
    return managerEmail.get();
  }

  public StringProperty managerEmailProperty()
  {
    return managerEmail;
  }

  public void propertyChange(PropertyChangeEvent evt){
    if (evt.getPropertyName().equals("notification")){
      getNotifications();
    }
    super.propertyChange(evt);
  }

  public ObservableList<NotificationTable> getNotificationList() {
    return notificationList;
  }
}
