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
import shared.exception.ServerException;

import java.io.IOException;
import java.sql.SQLException;

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


  public void start(Stage stage) throws Exception{
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

      if ("../view/movieList/movieListView.fxml".equals(viewToOpen))
      {
        loader.setLocation(getClass().getResource(viewToOpen));
        root = loader.load();
        MovieListController controller = loader.getController();
        controller.init();
        stage.setTitle("Movie List");
      }
      else if ("../view/showingList/showingListView.fxml".equals(viewToOpen))
      {
        loader.setLocation(getClass().getResource(viewToOpen));
        root = loader.load();
        ShowingListController controller = loader.getController();
        controller.init();
        stage.setTitle("Showing List");
      }

      else if ("../view/seatView/seatView.fxml".equals(viewToOpen))
      {

        loader.setLocation(getClass().getResource(viewToOpen));
        root = loader.load();
        SeatViewController controller = loader.getController();
        controller.init();

        stage.setTitle("SeatView");

      }
      else if ("../view/loginView/loginView.fxml".equals(viewToOpen))
      {
        loader.setLocation(getClass().getResource(viewToOpen));
        root = loader.load();
        LoginViewController controller = loader.getController();
        controller.init();

        stage.setTitle("login");

      }
      else if ("../view/createUserView/createUserView.fxml".equals(viewToOpen))
      {
        loader.setLocation(getClass().getResource(viewToOpen));
        root = loader.load();
        CreateUserViewController controller = loader.getController();
        controller.init();

        stage.setTitle("Create User");

      }
      else if ("../view/adminView/editView/editMovieView.fxml".equals(viewToOpen))
      {
        loader.setLocation(getClass().getResource(viewToOpen));
        root = loader.load();
        EditMovieController controller = loader.getController();
        controller.init();

        stage.setTitle("EditMovie");
      }
      else if ("../view/adminView/editView/editShowingView.fxml".equals(viewToOpen))
      {
        loader.setLocation(getClass().getResource(viewToOpen));
        root = loader.load();
        EditShowingController controller = loader.getController();
        controller.init();

        stage.setTitle("Edit Showing");
      }
      else if ("../view/adminView/editView/addShowingView.fxml".equals(viewToOpen))
      {
        loader.setLocation(getClass().getResource(viewToOpen));
        root = loader.load();
        AddShowingController controller = loader.getController();
        controller.init();

        stage.setTitle("Add Showing");
      }
      else if ("../view/adminView/adminView.fxml".equals(viewToOpen))
      {
        loader.setLocation(getClass().getResource(viewToOpen));
        root = loader.load();
        AdminViewController controller = loader.getController();
        controller.init();

        stage.setTitle("Admin");
      }

      else if ("../view/adminView/editView/editBookingView.fxml".equals(viewToOpen))
      {
        loader.setLocation(getClass().getResource(viewToOpen));
        root = loader.load();
        EditBookingView controller = loader.getController();
        controller.init();

        stage.setTitle("Edit Booking");
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

