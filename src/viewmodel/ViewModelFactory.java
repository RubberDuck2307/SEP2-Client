package viewmodel;

import model.Model;
import viewmodel.ProjectView.ProjectsViewModel;

public class ViewModelFactory
{
  private Model model;
  private TasksViewModel tasksViewModel;
  private ProjectsViewModel projectsViewModel;
  private ViewState viewState;
  public ViewModelFactory(Model model)
  {
    this.model = model;
    this.viewState = new ViewState();
    this.tasksViewModel = new TasksViewModel(model, viewState);
    this.projectsViewModel = new ProjectsViewModel(model, viewState);
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