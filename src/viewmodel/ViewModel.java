package viewmodel;

import javafx.collections.ObservableList;
import viewmodel.TaskView.TasksTable;

public interface ViewModel
{
  ObservableList<TasksTable> getAll();
}
