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

public class WorkerProfileViewModel extends ViewModelWithNavigationMenu {
    private ViewState viewState;

    private ObservableList<ProjectsTable> currentProjectsTable;

    private ProjectList projectList;

    private ObservableList<TasksTableForWorkerProfile> tasksTable;
    private TaskList taskList;

    private StringProperty workerName;

    private StringProperty workerRole;

    private StringProperty workerDateOfBirth;

    private StringProperty workerPhoneNumber;

    private StringProperty workerEmail;
    private StringProperty workerManagers;

    public WorkerProfileViewModel(Model model, ViewState viewState) {
        super(model);
        this.viewState = viewState;


        this.workerName = new SimpleStringProperty();
        this.workerRole = new SimpleStringProperty();
        this.workerDateOfBirth = new SimpleStringProperty();
        this.workerPhoneNumber = new SimpleStringProperty();
        this.workerEmail = new SimpleStringProperty();
        this.workerManagers = new SimpleStringProperty();

        this.currentProjectsTable = FXCollections.observableArrayList();


        this.tasksTable = FXCollections.observableArrayList();

        notification = new SimpleBooleanProperty(false);
    }

    public void load() {
        super.load();

        Employee employee = viewState.getEmployee();
        workerName.setValue(employee.getName());
        workerEmail.setValue(employee.getEmail());
        workerPhoneNumber.setValue(employee.getPhoneNumber());
        workerRole.setValue(employee.getRoleString());
        workerDateOfBirth.setValue(employee.getDob().toString());

        EmployeeList managersList = model.getAllWorkersManagersByWorkerWorkingNumber(employee.getWorkingNumber());
        String managers = "";
        for (int i = 0; i < managersList.size(); i++) {
            if (i == 0) {
                managers = managersList.get(i).getName();
            } else {
                managers = managers + ", " + managersList.get(i).getName();
            }

        }
        if (Objects.equals(managers, "")) {
            workerManagers.setValue("This worker is not assigned to any manager.");
        } else {
            workerManagers.setValue(managers);
        }

        projectList = model.getAllProjectsByWorkingNumber(employee.getWorkingNumber());
        currentProjectsTable.clear();
        for (int i = 0; i < projectList.size(); i++) {
            currentProjectsTable.add(new ProjectsTable(projectList.get(i)));
        }
        taskList = model.getAllTasksByUserId(employee.getWorkingNumber());
        tasksTable.clear();
        for (int i = 0; i < taskList.size(); i++) {
            tasksTable.add(new TasksTableForWorkerProfile(taskList.getTask(i), model.getProjectById(taskList.getTask(i).getProjectId())));
        }

    }

    public void reset() {
        super.reset();
        load();
    }

    public ObservableList<ProjectsTable> getCurrentProjectsTableTable() {
        return currentProjectsTable;
    }

    public ObservableList<TasksTableForWorkerProfile> getTaskTable() {
        return tasksTable;
    }

    public StringProperty workerManagersProperty() {
        return workerManagers;
    }

    public StringProperty workerNameProperty() {
        return workerName;
    }


    public StringProperty workerRoleProperty() {
        return workerRole;
    }

    public StringProperty workerDateOfBirthProperty() {
        return workerDateOfBirth;
    }


    public StringProperty workerPhoneNumberProperty() {
        return workerPhoneNumber;
    }


    public StringProperty workerEmailProperty() {
        return workerEmail;
    }

    public boolean isWorkerWoman() {
        Employee employeeManager = viewState.getEmployee();
        return Objects.equals(employeeManager.getGender(), "F");
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }
}


