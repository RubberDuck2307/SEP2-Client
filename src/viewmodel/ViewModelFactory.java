package viewmodel;

import model.Model;
import viewmodel.AddProjectView.AddProjectViewModel;
import viewmodel.ProjectView.ProjectsViewModel;
import viewmodel.WorkerView.*;

public class ViewModelFactory
{
    private Model model;
    private TasksViewModel tasksViewModel;
    private ProjectsViewModel projectsViewModel;
    private AssignWorkersToTaskViewModel assignWorkersToTaskViewModel;
    private AssignEmployeesToProjectViewModel assignEmployeesToProjectViewModel;
    private AssignWorkersToProjectManagerViewModel assignWorkersToProjectManagerViewModel;
    private EditTaskViewModel editTaskViewModel;
    private AddTaskViewModel addTaskViewModel;
    private CreateUserProfileViewModel createUserProfileViewModel;
    private AddProjectViewModel addProjectViewModel;
    private WorkersViewModel workersViewModel;
    private ViewState viewState;
    private LoginViewModel loginViewModel;
    private ProjectManagerProfileViewModel projectManagerProfileViewModel;
    private HomeViewModel homeViewModel;
    private HrAndMainManagerProfileViewModel hrAndMainManagerProfileViewModel;
    private WorkerProfileViewModel workerProfileViewModel;
    private WorkerHomeViewModel workerHomeViewModel;
    public ViewModelFactory(Model model)
    {
        this.model = model;
        this.viewState = new ViewState();
        this.tasksViewModel = new TasksViewModel(model, viewState);
        this.projectsViewModel = new ProjectsViewModel(model, viewState);
        this.addTaskViewModel = new AddTaskViewModel(model, viewState);
        this.editTaskViewModel = new EditTaskViewModel(model, viewState);
        this.assignWorkersToTaskViewModel = new AssignWorkersToTaskViewModel(model, viewState);
        this.assignEmployeesToProjectViewModel = new AssignEmployeesToProjectViewModel(model, viewState);
        this.assignWorkersToProjectManagerViewModel = new AssignWorkersToProjectManagerViewModel(model, viewState);
        this.addProjectViewModel = new AddProjectViewModel(model, viewState);
        this.createUserProfileViewModel = new CreateUserProfileViewModel(model, viewState);
        loginViewModel = new LoginViewModel(model);
        this.workersViewModel = new WorkersViewModel(model, viewState);
        this.projectManagerProfileViewModel = new ProjectManagerProfileViewModel(model, viewState);
        this.homeViewModel = new HomeViewModel(model, viewState);
        this.hrAndMainManagerProfileViewModel = new HrAndMainManagerProfileViewModel(model,viewState);
        this.workerProfileViewModel = new WorkerProfileViewModel(model,viewState);
        this.workerHomeViewModel= new WorkerHomeViewModel(model,viewState);
    }
    
    public Model getModel()
    {
        return model;
    }
    
    public ProjectsViewModel getProjectsViewModel()
    {
        return projectsViewModel;
    }

    public WorkerProfileViewModel getWorkerProfileViewModel()
    {
        return workerProfileViewModel;
    }

    public HrAndMainManagerProfileViewModel getHrAndMainManagerProfileViewModel()
    {
        return hrAndMainManagerProfileViewModel;
    }

    public TasksViewModel getTasksViewModel()
    {
        return tasksViewModel;
    }
    
    public ProjectManagerProfileViewModel getProjectManagerProfileViewModel()
    {
        return projectManagerProfileViewModel;
    }
    
    public WorkersViewModel getWorkersViewModel()
    {
        return workersViewModel;
    }
    
    public AssignWorkersToTaskViewModel getAssignWorkersToTaskViewModel()
    {
        return assignWorkersToTaskViewModel;
    }
    
    public AssignEmployeesToProjectViewModel getAssignWorkersToProjectViewModel()
    {
        return assignEmployeesToProjectViewModel;
    }

    public AssignWorkersToProjectManagerViewModel getAssignWorkersToProjectManagerViewModel()
    {
        return assignWorkersToProjectManagerViewModel;
    }

    public AddTaskViewModel getAddTaskViewModel()
    {
        return addTaskViewModel;
    }
    
    public EditTaskViewModel getEditTaskViewModel()
    {
        return editTaskViewModel;
    }
    
    public AddProjectViewModel getAddProjectViewModel()
    {
        return addProjectViewModel;
    }
    
    public CreateUserProfileViewModel getCreateUserProfileViewModel()
    {
        return createUserProfileViewModel;
    }
    public LoginViewModel getLoginViewModel()
    {
        return loginViewModel;
    }
    
    public HomeViewModel getHomeViewModel()
    {
        return homeViewModel;
    }

    public WorkerHomeViewModel getWorkerHomeViewModel()
    {
        return workerHomeViewModel;
    }
}