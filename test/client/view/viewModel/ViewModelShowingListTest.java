package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelShowingListTest
{
  private DAOTestSetup setup = new DAOTestSetup();
  private ViewModelShowingList viewModel = new ViewModelShowingList();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
    viewModel.setSelectedMovie(setup.getMovie());
  }

  @Test void testIfWeGetMovieTitle() {
    assertEquals(setup.getMovie().getMovieTitle(), viewModel.getMovieTitle());
  }


}