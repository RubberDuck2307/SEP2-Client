package viewmodel;

import model.Project;
import model.Task;
import view.ProjectsViewController;
import view.ViewHandler;

import java.time.LocalDate;
import java.util.ArrayList;

public class ViewState
{
  private Project project;
  private Task task;

  public ViewState(){
    this.project=new Project(1L, "HonzaRules", "Honza commands slave Alex to do Class Diagram", LocalDate.now(), new ArrayList<>());
    this.task=new Task(1L, "AnnaBigDicc", "Anna has a long shaft. :)",
        LocalDate.now(), 69, "HIGH", "TO DO", 1L, LocalDate.now(), new ArrayList<>());
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
