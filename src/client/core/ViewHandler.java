package client.core;


import client.view.Controller;
import client.view.loginView.LoginViewController;
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
  private Controller state;


  private ViewHandler(){}

  public static ViewHandler getInstance()
  {
    if (viewHandler == null)
    {
      viewHandler = new ViewHandler();
    }
    return viewHandler;
  }

  /**
   * setter stage og gør at client bliver terminated når vinduet lukkes
   * @param stage
   */
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
    setState(new LoginViewController());
    openView();
    //openView("Login");
  }

  /**
   * Åbner view og kalder controllers init metode. View skal være del af ViewMap's
   * HashMap
   *
   * @param viewToOpen (Skrevet som title på vinduet, se ViewMap)
   *                   eks. "Movie List" for "movieListView.fxml"
   */
  public void openView()
  {
    FXMLLoader loader = new FXMLLoader();
    Parent root = null;
    try
    {
      String pathToView = state.getPath(); //lookup i Hashmap
      loader.setLocation(getClass().getResource("../view/" + pathToView + "View.fxml"));
      root = loader.load();
      Controller controller = loader.getController();
      controller.init();
      stage.setTitle(state.getTitle());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }


  public void setState(Controller state){
    this.state = state;
  }
}

