package client.view.viewModel;

import client.model.ClientModelShowing;
import client.model.ClientModelShowingManager;
import client.network.Client;
import client.network.RMIClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.dao.ShowingDAOImpl;
import shared.exception.ServerException;
import shared.transferobjects.Hall;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelAddShowingTest
{
  private RMIClient client = new RMIClient();
  private ClientModelShowing model = new ClientModelShowingManager(client);
  private ShowingDAOImpl dao = new ShowingDAOImpl();
  private Hall hall = new Hall("A", 5, 5);
  private Showing showing = null;
  private String movieTitle = "Jackass";
  private Movie movie = new Movie(1, movieTitle);
  Timestamp time = new Timestamp(121, 4, 14,
  13, 30, 0, 0);

  @BeforeEach void setup() {
    if (showing == null) {
      showing = new Showing(1 ,movie, time, hall);
    }
  }

  @Test void testIfShowingIsCreated() {
    assertNotNull(showing);
  }

  @Test void testIfShowingIsCreatedWithCorrectInfo() {
    assertEquals(1, showing.getId());
    assertEquals(movieTitle, showing.getMovie().getMovieTitle());
    assertEquals(time, showing.getTimestamp());
    assertEquals("A", showing.getHall().getHallNo());
  }

  @Test void testIfShowingIsAddedInDatabase()
      throws SQLException, RemoteException, ServerException
  {
    client.startClient();
    model.addShowing(showing);
    ArrayList<Showing> showingList = dao.getAllShowings(movie);
    assertEquals(movieTitle, showingList.get(0).getMovie().getMovieTitle());
  }


}