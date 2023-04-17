package viewmodel;

import model.Model;
import view.ProjectsViewController;

public class ViewModelFactory
{
  private Model model;
  private TasksViewModel tasksViewModel;
  private ProjectsViewModel projectsViewModel;

  public ViewModelFactory(Model model)
  {
    this.model = model;
    this.tasksViewModel = new TasksViewModel(model);
    this.projectsViewModel = new ProjectsViewModel(model);
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
}