package client.view.viewModel;

import client.model.ClientModelShowing;
import client.model.ClientModelShowingManager;
import client.network.RMIClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import shared.transferobjects.Showing;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelAddShowingTest
{
  private RMIClient client = new RMIClient();
  private ClientModelShowing model = new ClientModelShowingManager(client);
  private ViewModelAddShowing viewModel = new ViewModelAddShowing();
  private DAOTestSetup setup = new DAOTestSetup();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test void testIfShowingIsCreated() {
    assertNotNull(setup.getShowing());
  }

  @Test void testIfShowingIsCreatedWithCorrectInfo() {
    assertEquals(1, setup.getShowing().getId());
    assertEquals(setup.getMovieTitle(), setup.getShowing().getMovie().getMovieTitle());
    assertEquals(setup.getTime(), setup.getShowing().getTimestamp());
    assertEquals("A", setup.getShowing().getHall().getHallNo());
  }

  @Test void testIfShowingIsAddedInDatabase()
      throws  ServerException
  {
    client.startClient();
    setup.getTime().setHours(18);
    viewModel.setSelectedMovie(setup.getMovie());
    viewModel.addShowing();
    ArrayList<Showing> showingList = model.getShowingList(setup.getMovie());
    assertEquals(setup.getMovieTitle(), showingList.get(0).getMovie().getMovieTitle());
  }

  @Test void testIfItsTheRightHall()
      throws ServerException
  {
    assertEquals(setup.getHall().getHallNo(), viewModel.getHallByNumber("A").getHallNo());
  }




}