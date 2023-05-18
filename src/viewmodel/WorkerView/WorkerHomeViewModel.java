package viewmodel.WorkerView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import model.*;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.ViewModel;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Objects;

public class WorkerHomeViewModel extends ViewModelWithNavigationMenu
{
  private final ObservableList<ProjectsTable> currentProjectsTable;
  private final ObservableList<TasksTableForWorkerProfile> tasksTable;

  private final StringProperty workerName;

  public WorkerHomeViewModel(Model model)
  {
    super(model);

    this.workerName = new SimpleStringProperty();

    this.currentProjectsTable = FXCollections.observableArrayList();

    this.tasksTable = FXCollections.observableArrayList();

  }

  public void reset()
  {
    super.reset();
    load();
  }

  public void load()
  {
    super.load();
    Employee worker = model.getUser();
    workerName.setValue("Welcome back, " + worker.getName() + "!");

    EmployeeList managersList = model.getAllWorkersManagersByWorkerWorkingNumber(worker.getWorkingNumber());
    String managers = "";
    for(int i = 0; i<managersList.size(); i++){
      if(i==0){
        managers = managersList.get(i).getName();
      }
      else{
        managers = managers + ", " + managersList.get(i).getName();
      }

    }
    ProjectList projectList = model.getAllProjectsByWorkingNumber(worker.getWorkingNumber());
    currentProjectsTable.clear();
    for (int i = 0; i < projectList.size(); i++)
    {
      currentProjectsTable.add(new ProjectsTable(projectList.get(i)));
    }
    TaskList taskList = model.getAllTasksByUserId(worker.getWorkingNumber());
    tasksTable.clear();
    for (int i = 0; i < taskList.size(); i++)
    {
      tasksTable.add(new TasksTableForWorkerProfile(taskList.getTask(i),model.getProjectById(taskList.getTask(i).getProjectId())));
    }

  }

  public ObservableList<ProjectsTable> getCurrentProjectsTableTable(){return currentProjectsTable;}
  public ObservableList<TasksTableForWorkerProfile> getTaskTable(){return tasksTable;}

  public StringProperty workerNameProperty()
  {
    return workerName;
  }



  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    super.propertyChange(evt);
  }
}
