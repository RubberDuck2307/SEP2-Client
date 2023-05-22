package view;

import javafx.scene.layout.Region;
import viewmodel.ViewModel;

/**
 * Interface for all view controllers.
 */
public interface ViewController
{
  void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root);
  Region getRoot();
  void reset();
}
