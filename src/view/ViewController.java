package view;

import javafx.scene.layout.Region;
import viewmodel.ViewModel;

public interface ViewController
{
  void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root);
  Region getRoot();
  void reset();
}
