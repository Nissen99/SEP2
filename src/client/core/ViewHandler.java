package client.core;

import client.view.adminView.AdminViewController;
import client.view.adminView.editView.AddShowingController;
import client.view.adminView.editView.EditMovieController;
import client.view.adminView.editView.EditShowingController;
import client.view.bookingView.BookingViewController;
import client.view.loginView.LoginViewController;
import client.view.movieList.MovieListController;
import client.view.seatView.SeatViewController;
import client.view.showingList.ShowingListController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ViewHandler
{

  private static ViewHandler viewHandler;
  private ViewModelFactory viewModelFactory;
  private Stage stage;

  private ViewHandler(ViewModelFactory instance){
    this.viewModelFactory = instance;
  }

  public void setStage(Stage stage)
  {
    this.stage = stage;
  }



  public static ViewHandler getInstance(){
    if (viewHandler == null){
      viewHandler = new ViewHandler(ViewModelFactory.getInstance());
    }
    return viewHandler;
  }


  public void start(Stage stage) throws Exception{
    setStage(stage);
    openView("../view/loginView/loginView.fxml");
  }

  public void openView(String viewToOpen) throws IOException, SQLException
  {
    Scene scene = null;
    FXMLLoader loader = new FXMLLoader();
    Parent root = null;

    if ("../view/movieList/movieListView.fxml".equals(viewToOpen))
    {
      loader.setLocation(getClass().getResource(viewToOpen));
      root = loader.load();
      MovieListController controller = loader.getController();
      controller.init();
      stage.setTitle("Movie List");
    }
    else if ("../view/showingList/showingListView.fxml".equals(viewToOpen)){
      loader.setLocation(getClass().getResource(viewToOpen));
      root = loader.load();
      ShowingListController controller = loader.getController();
      controller.init();
      stage.setTitle("Showing List");
    }else if ("../view/bookingView/bookingView.fxml".equals(viewToOpen)){
      loader.setLocation(getClass().getResource(viewToOpen));
      root = loader.load();
      BookingViewController controller = loader.getController();

      stage.setTitle("Booking");
    } else if ("../view/seatView/seatView.fxml".equals(viewToOpen)){
      loader.setLocation(getClass().getResource(viewToOpen));
      root = loader.load();
      SeatViewController controller = loader.getController();
      controller.init();

      stage.setTitle("SeatView");

    } else if ("../view/loginView/loginView.fxml".equals(viewToOpen)){
      loader.setLocation(getClass().getResource(viewToOpen));
      root = loader.load();
      LoginViewController controller = loader.getController();
      controller.init();

      stage.setTitle("login");

    } else if ("../view/adminView/adminView.fxml".equals(viewToOpen)){
      loader.setLocation(getClass().getResource(viewToOpen));
      root = loader.load();
      AdminViewController controller = loader.getController();
      controller.init();

      stage.setTitle("login");

    }
    else if ("../view/adminView/editView/editMovieView.fxml".equals(viewToOpen)){
      loader.setLocation(getClass().getResource(viewToOpen));
      root = loader.load();
      EditMovieController controller = loader.getController();
      controller.init();

      stage.setTitle("EditMovie");
    }
    else if ("../view/adminView/editView/editShowingView.fxml".equals(viewToOpen)){
      loader.setLocation(getClass().getResource(viewToOpen));
      root = loader.load();
      EditShowingController controller = loader.getController();
      controller.init();

      stage.setTitle("Edit Showing");
    }
    else if ("../view/adminView/editView/addShowingView.fxml".equals(viewToOpen)){
      loader.setLocation(getClass().getResource(viewToOpen));
      root = loader.load();
      AddShowingController controller = loader.getController();
      controller.init();

      stage.setTitle("Add Showing");
    }

    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}

