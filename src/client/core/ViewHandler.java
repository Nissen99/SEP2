package client.core;

import client.util.ViewMap;
import client.view.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Singleton
 */

public class ViewHandler
{

  private static ViewHandler viewHandler;
  private Stage stage;


  private ViewHandler()
  {

  }

  public static ViewHandler getInstance()
  {
    if (viewHandler == null)
    {
      viewHandler = new ViewHandler();
    }
    return viewHandler;
  }

  public void setStage(Stage stage)
  {
    this.stage = stage;
    stage.setOnCloseRequest(windowEvent -> {
      System.exit(0);
    });

  }

  public void start(Stage stage)
  {
    setStage(stage);
    openView("Login");
  }

  public void openView(String viewToOpen)
  {
    FXMLLoader loader = new FXMLLoader();
    Parent root = null;

    try
    {
      String pathToView = ViewMap.getInstance().getPath(viewToOpen);
      loader.setLocation(getClass().getResource("../view/" + pathToView + "View.fxml"));
      root = loader.load();
      Controller controller = loader.getController();
      controller.init();
      stage.setTitle(viewToOpen);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();


  }
}

