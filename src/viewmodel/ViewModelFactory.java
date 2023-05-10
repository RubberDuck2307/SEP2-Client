package viewmodel;

import model.Model;
import viewmodel.AddProjectView.AddProjectViewModel;
import viewmodel.ProjectView.ProjectsViewModel;
import viewmodel.WorkerView.WorkersViewModel;

public class ViewModelFactory
{
  private Model model;
  private TasksViewModel tasksViewModel;
  private ProjectsViewModel projectsViewModel;
  private AssignWorkersToTaskViewModel assignWorkersToTaskViewModel;
  private EditTaskViewModel editTaskViewModel;
  private AddTaskViewModel addTaskViewModel;
  private CreateUserProfileViewModel createUserProfileViewModel;
  private AddProjectViewModel addProjectViewModel;
  private WorkersViewModel workersViewModel;
  private ViewState viewState;
  public ViewModelFactory(Model model)
  {
    this.model = model;
    this.viewState = new ViewState();
    this.tasksViewModel = new TasksViewModel(model, viewState);
    this.projectsViewModel = new ProjectsViewModel(model, viewState);
    this.addTaskViewModel = new AddTaskViewModel(model, viewState);
    this.editTaskViewModel = new EditTaskViewModel(model, viewState);
    this.assignWorkersToTaskViewModel = new AssignWorkersToTaskViewModel(model, viewState);
    this.addProjectViewModel = new AddProjectViewModel(model, viewState);
    this.createUserProfileViewModel = new CreateUserProfileViewModel(model, viewState);
    this.workersViewModel = new WorkersViewModel(model,viewState);
  }

  public Model getModel()
  {
    return model;
  }

  public ProjectsViewModel getProjectsViewModel()
  {
    return projectsViewModel;
  }

  public TasksViewModel getTasksViewModel()
  {
    return tasksViewModel;
  }

  public WorkersViewModel getWorkersViewModel()
  {
    return workersViewModel;
  }

  public AssignWorkersToTaskViewModel getAssignWorkersToTaskViewModel()
  {
    return assignWorkersToTaskViewModel;
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
}