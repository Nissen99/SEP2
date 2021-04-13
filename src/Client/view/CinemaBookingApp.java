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
    Movie movie = ModelFactory.getInstance().getModel().addMovie(new Movie(1,"MOVIE"));

    //TODO Fjern den her, det er til test
    Date date = new Date();
    ModelFactory.getInstance().getModel().addShowing(new Showing(1,
        movie,new Timestamp(date.getTime())));


    ViewHandler.getInstance().start(stage);
  }
}
