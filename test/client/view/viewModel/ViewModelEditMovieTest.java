package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;

import java.rmi.RemoteException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelEditMovieTest
{
  private DAOTestSetup setup = new DAOTestSetup();
  private ViewModelEditMovie viewModel = new ViewModelEditMovie();

  @BeforeEach void setup() throws SQLException, ServerException
  {
    setup.setup();
  }

  @Test void testIfMovieIsCreated() {
    assertNotNull(setup.getMovie());
  }

  @Test void testIfMovieIsCreatedWithCorrectInfo() {
    assertEquals(setup.getMovieTitle(), setup.getMovie().getMovieTitle());
  }

  @Test void testIfMovieIsCreatedInDatabase()
      throws SQLException, RemoteException, ServerException
  {
    assertEquals(setup.getMovieTitle(), viewModel.getAllMovies().get(0).getMovieTitle());
  }

  @Test void testRemoveMovie()
      throws RemoteException, SQLException, ServerException
  {
    viewModel.removeMovie(setup.getMovie());
    assertEquals(0, viewModel.getAllMovies().size());
  }
}