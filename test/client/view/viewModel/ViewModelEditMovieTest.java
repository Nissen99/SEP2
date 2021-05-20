package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import static org.junit.jupiter.api.Assertions.*;

class ViewModelEditMovieTest
{
  private VMTestSetup setup = new VMTestSetup();
  private ViewModelEditMovie viewModel = new ViewModelEditMovie();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test void testIfMovieIsCreated() {
    assertNotNull(setup.getMovie());
  }

  @Test void testIfMovieIsCreatedWithCorrectInfo() {
    assertEquals(setup.getMovieTitle(), setup.getMovie().getMovieTitle());
  }

  @Test void testIfMovieIsCreatedInDatabase() throws ServerException
  {
    assertEquals(setup.getMovieTitle(), viewModel.getAllMovies().get(0).getMovieTitle());
  }

  @Test void testRemoveMovie() throws ServerException
  {
    viewModel.removeMovie(setup.getMovie());
    assertEquals(setup.getMovieList().size()-1, viewModel.getAllMovies().size());
  }
}