package viewmodel;

import model.Model;
import viewmodel.AddProjectView.AddProjectViewModel;
import viewmodel.ProjectView.ProjectsViewModel;

public class ViewModelFactory
{
  private Model model;
  private TasksViewModel tasksViewModel;
  private ProjectsViewModel projectsViewModel;
  private AssignWorkersToTaskViewModel assignWorkersToTaskViewModel;
  private EditTaskViewModel editTaskViewModel;
  private AddTaskViewModel addTaskViewModel;
  private AddProjectViewModel addProjectViewModel;
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
}