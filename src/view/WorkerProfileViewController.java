package view;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import viewmodel.ViewModel;
import viewmodel.WorkerView.ProjectManagerProfileViewModel;
import viewmodel.WorkerView.WorkerProfileViewModel;

public class WorkerProfileViewController implements ViewController
{
  private Region root;
  private WorkerProfileViewModel viewModel;
  private ViewHandler viewHandler;
  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {

  }

  @Override public Region getRoot()
  {
    return null;
  }

  @Override public void reset()
  {

  }

  public void openWorkersView()
  {
    viewHandler.openView("workers");
  }
  public void openProjects()
  {
    viewHandler.openView("projects");
  }
}
