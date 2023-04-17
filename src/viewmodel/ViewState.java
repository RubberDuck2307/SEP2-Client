package viewmodel;

import model.Project;
import model.Task;
import view.ProjectsViewController;
import view.ViewHandler;

public class ViewState
{
  private Project project;
  private Task task;

  public ViewState(){
    this.project=null;
    this.task=null;
  }

  public Task getTask()
  {
    return task;
  }

  public Project getProject()
  {
    return project;
  }

  public void setProject(Project project)
  {
    this.project = project;
  }

  public void setTask(Task task)
  {
    this.task = task;
  }
}
