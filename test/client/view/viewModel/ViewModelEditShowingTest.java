package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewModelEditShowingTest
{

  private VMTestSetup setup = new VMTestSetup();
  private ViewModelEditShowing viewModel = new ViewModelEditShowing();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }


  @Test
  void testIfWeGetAllShowings() throws ServerException
  {
    viewModel.setSelectedMovie(setup.getMovie());
    assertEquals(setup.getShowingList().size(),viewModel.getAllShowings().size());

  }


  @Test void testIfShowingIsRemoved() throws ServerException
  {
    viewModel.setSelectedMovie(setup.getMovie());
    viewModel.removeShowing(setup.getShowing());
    assertEquals(setup.getShowingList().size()-1, viewModel.getAllShowings().size());
  }


}