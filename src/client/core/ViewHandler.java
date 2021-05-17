package client.core;

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

/**
 * Singleton
 */

public class ViewHandler
{

  private static ViewHandler viewHandler;
  private Stage stage;

  private ViewHandler(){}


  public static ViewHandler getInstance(){
    if (viewHandler == null){
      viewHandler = new ViewHandler();
    }
    return viewHandler;
  }

  public void setStage(Stage stage)
  {
    this.stage = stage;
  }


  public void start(Stage stage){
    setStage(stage);
    openView("../view/loginView/loginView.fxml");
  }

  public void openView(String viewToOpen)
  {
    Scene scene;
    FXMLLoader loader = new FXMLLoader();
    Parent root = null;


    try
    {
      loader.setLocation(getClass().getResource(viewToOpen));
      root = loader.load();

      switch (viewToOpen)
      {
        case "../view/movieList/movieListView.fxml" -> {
          MovieListController controller = loader.getController();
          controller.init();
          stage.setTitle("Movie List");
        }
        case "../view/showingList/showingListView.fxml" -> {
          ShowingListController controller = loader.getController();
          controller.init();
          stage.setTitle("Showing List");
        }
        case "../view/seatView/seatView.fxml" -> {
          SeatViewController controller = loader.getController();
          controller.init();
          stage.setTitle("SeatView");
        }
        case "../view/loginView/loginView.fxml" -> {
          LoginViewController controller = loader.getController();
          controller.init();
          stage.setTitle("login");
        }
        case "../view/createUserView/createUserView.fxml" -> {
          CreateUserViewController controller = loader.getController();
          controller.init();
          stage.setTitle("Create User");
        }
        case "../view/adminView/editView/editMovieView.fxml" -> {
          EditMovieController controller = loader.getController();
          controller.init();
          stage.setTitle("EditMovie");
        }
        case "../view/adminView/editView/editShowingView.fxml" -> {
          EditShowingController controller = loader.getController();
          controller.init();
          stage.setTitle("Edit Showing");
        }
        case "../view/adminView/editView/addShowingView.fxml" -> {
          AddShowingController controller = loader.getController();
          controller.init();

          stage.setTitle("Add Showing");
        }
        case "../view/adminView/adminView.fxml" -> {
          AdminViewController controller = loader.getController();
          controller.init();

          stage.setTitle("Admin");
        }
        case "../view/adminView/editView/editBookingView.fxml" -> {
          EditBookingView controller = loader.getController();
          controller.init();
          stage.setTitle("Edit Booking");
        }
      }


    }catch (IOException e){

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

