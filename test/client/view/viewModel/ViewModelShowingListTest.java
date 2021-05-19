package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelShowingListTest
{
  private VMTestSetup setup = new VMTestSetup();
  private ViewModelShowingList viewModel = new ViewModelShowingList();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
    viewModel.setSelectedMovie(setup.getMovie());
    viewModel.setSelectedShowing(setup.getShowing());
  }

  @Test void testIfWeGetMovieTitle() {
    assertEquals(setup.getMovie().getMovieTitle(), viewModel.getMovieTitle());
  }

  @Test void testGetAllShowings() throws ServerException
  {
    assertEquals(setup.getShowingList().size(), viewModel.getFutureShowings().size());
  }

  @Test void testGetSelectedShowing() {
    assertEquals(setup.getShowing(), viewModel.getSelectedShowing());
  }

}