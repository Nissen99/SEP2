package Client.core;

import Client.view.bookingView.BookingViewController;
import Client.view.movieList.MovieListController;
import Client.view.seatView.SeatViewController;
import Client.view.showingList.ShowingListController;
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
    openView("../view/movieList/movieListView.fxml");
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

      stage.setTitle("SeatView");
    }

    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}

