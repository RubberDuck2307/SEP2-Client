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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class MainManagerHomeViewModel extends ViewModelWithNavigationMenu
{
  private ViewState viewState;
  private StringProperty managerName;
  private StringProperty managerRole;
  private StringProperty managerDateOfBirth;
  private StringProperty managerPhoneNumber;
  private StringProperty managerEmail;
  private StringProperty workerName2;




  public MainManagerHomeViewModel(Model model, ViewState viewState)
  {
    super(model);

    this.viewState = viewState;

    this.managerName = new SimpleStringProperty();
    this.managerRole = new SimpleStringProperty();
    this.managerDateOfBirth = new SimpleStringProperty();
    this.managerPhoneNumber = new SimpleStringProperty();
    this.managerEmail = new SimpleStringProperty();
    this.workerName2 = new SimpleStringProperty();

  }

  public void reset(){
    super.reset();

  }
  public void load()
  {
    super.load();
    Employee employee = model.getUser();
    managerName.setValue(employee.getName());
    managerEmail.setValue(employee.getEmail());
    managerPhoneNumber.setValue(employee.getPhoneNumber());
    managerRole.setValue(employee.getRole().toString());
    managerDateOfBirth.setValue(employee.getDob().toString());
    headline();

  }

  public String getManagerName()
  {
    return managerName.get();
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
  public StringProperty getWorkerName2()
  {
    return workerName2;
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

}
