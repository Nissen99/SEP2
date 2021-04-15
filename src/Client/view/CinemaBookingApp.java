package Client.view;

import Client.core.ModelFactory;
import Client.core.ViewHandler;
import DatabaseConnection.dao.ResetDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import shared.Hall;
import shared.Movie;
import shared.Showing;

import java.sql.Timestamp;
import java.util.Date;

public class CinemaBookingApp extends Application
{

  @Override public void start(Stage stage) throws Exception
  {

    ResetDAO resetDAO = new ResetDAO();
    resetDAO.resetDB();

    //TODO Fjern den her, det er til test
    Date date = new Date();

    Movie starWarsX = ModelFactory.getInstance().getModel().addMovie(new Movie("Star Wars X"));
    Movie spiderMan = ModelFactory.getInstance().getModel().addMovie(new Movie("SpiderMan 3"));


    Hall hall1 = ModelFactory.getInstance().getModel().addHall(new Hall(1, 10, 10));
    Hall hall2 = ModelFactory.getInstance().getModel().addHall(new Hall(2, 10, 5));


    Timestamp rightNow = new Timestamp(date.getTime());

    Showing showingOfStarWarsX = new Showing(starWarsX, rightNow, hall1);
    Showing showingOfSpiderMan = new Showing(spiderMan, rightNow, hall2);


    ModelFactory.getInstance().getModel().addShowing(showingOfStarWarsX);
    ModelFactory.getInstance().getModel().addShowing(showingOfSpiderMan);



    ViewHandler.getInstance().start(stage);
  }
}
