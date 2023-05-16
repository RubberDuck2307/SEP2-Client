import javafx.application.Application;
import javafx.stage.Stage;
import mediator.Client;
import mediator.ClientInterface;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyApplication extends Application
{
  public void start(Stage primaryStage) throws MalformedURLException, NotBoundException, RemoteException {
    ClientInterface client = new Client();
    Model model = new ModelManager(client);
    //System.out.println(model.getAllTasksOfProject(1L));
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(primaryStage);
  }
}
