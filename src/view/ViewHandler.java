package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModel;
import viewmodel.ViewModelFactory;

import java.util.HashMap;

public class ViewHandler {
    private Scene currentScene;
    private Stage primaryStage;

    private String lastPageId;
    private String currentPageId;
    private final ViewControllerLoader viewControllerLoader;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        currentScene = new Scene(new Region());
        lastPageId = "";
        currentPageId = "login";
        viewControllerLoader = new ViewControllerLoader(this, viewModelFactory);

    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openView("login");
    }

    public void openView(String id) {
        Region root = viewControllerLoader.loadViewController(id);
        lastPageId = currentPageId;
        currentPageId = id;
        currentScene.setRoot(root);
        String title = "";
        if (root.getUserData() != null) {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }
    public void openLastWindow() {
        openView(lastPageId);
    }
}
