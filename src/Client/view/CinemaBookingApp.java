package Client.view;

import Client.core.ModelFactory;
import Client.core.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.sql.Timestamp;
import java.util.Date;

public class CinemaBookingApp extends Application
{

  @Override public void start(Stage stage) throws Exception
  {


    //TODO Fjern den her, det er til test
    ModelFactory.getInstance().getModel().addMovie(new Movie("MOVIE"));

    //TODO Fjern den her, det er til test
    Date date = new Date();
    ModelFactory.getInstance().getModel().addShowing(new Showing(
        ModelFactory.getInstance().getModel().getMovieList().get(0),new Timestamp(date.getTime())));


    ViewHandler.getInstance().start(stage);
  }
}
