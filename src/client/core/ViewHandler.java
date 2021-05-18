package client.core;

import client.view.Controller;
import client.view.adminView.AdminViewController;
import client.view.adminView.editView.AddShowingController;
import client.view.adminView.editView.EditBookingView;
import client.view.adminView.editView.EditMovieController;
import client.view.adminView.editView.EditShowingController;
import client.view.createUserView.CreateUserViewController;
import client.view.loginView.LoginViewController;
import client.view.movieList.MovieListController;
import client.view.seatView.SeatViewController;
import client.view.showingList.ShowingListController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton
 */

public class ViewHandler
{

  private Map<String, String> viewMap;
  private static ViewHandler viewHandler;
  private Stage stage;

  private ViewHandler()
  {
    viewMap = new HashMap<>();
    viewMap.put("Movie List", "movieList/movieList");
    viewMap.put("Showing List", "showingList/showingList");
    viewMap.put("Seat", "seatView/seat");
    viewMap.put("Login", "loginView/login");
    viewMap.put("Create User", "createUserView/createUser");
    viewMap.put("Edit Movie", "adminView/editView/editMovie");
    viewMap.put("Edit Showing", "adminView/editView/editShowing");
    viewMap.put("Add Showing", "adminView/editView/addShowing");
    viewMap.put("Admin", "adminView/admin");
    viewMap.put("Edit Booking", "adminView/editView/editBooking");
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
  }

  public void start(Stage stage)
  {
    setStage(stage);
    openView("Login");
  }

  public void openView(String viewToOpen)
  {
    Scene scene;
    FXMLLoader loader = new FXMLLoader();
    Parent root = null;

    try
    {
      String pathToView = viewMap.get(viewToOpen);
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

    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

    stage.setOnCloseRequest(windowEvent -> {
      System.exit(0);
    });

  }
}

