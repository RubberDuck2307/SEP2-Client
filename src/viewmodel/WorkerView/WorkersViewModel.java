package viewmodel.WorkerView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import viewmodel.TaskView.TasksTable;
import viewmodel.ViewModel;
import viewmodel.ViewState;

import java.util.ArrayList;

public class WorkersViewModel implements ViewModel
{
  private Model model;
  private ViewState viewState;
  private ObservableList<WorkersTable> workersTables;
  private StringProperty name;
  private StringProperty role;
  private IntegerProperty number;
  private StringProperty email;
  private EmployeeList employeeList;

  public WorkersViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.workersTables = FXCollections.observableArrayList();
    this.name = new SimpleStringProperty();
    this.role = new SimpleStringProperty();
    this.number = new SimpleIntegerProperty();
    this.email = new SimpleStringProperty();
  }
  public void load()
  {

    employeeList = model.getAllEmployees();
    workersTables.clear();
    for (int i = 0; i < employeeList.size(); i++)
    {
      workersTables.add(new viewmodel.WorkerView.WorkersTable(employeeList.getEmployee(i)));
    }

  }
  public void chooseWorker(int workingNumber)
  {
    Employee employee = model.getEmployeeByWorkingNumber(workingNumber);
    viewState.setEmployee(employee);
    workersTables.clear();
  }

  public ObservableList<viewmodel.WorkerView.WorkersTable> getWorkersTable(){return workersTables;}
}
