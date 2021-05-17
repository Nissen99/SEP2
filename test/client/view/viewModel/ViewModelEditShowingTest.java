package client.view.viewModel;

import client.network.RMIClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewModelEditShowingTest
{

  private DAOTestSetup setup = new DAOTestSetup();
  private ViewModelEditShowing viewModel = new ViewModelEditShowing();
  private RMIClient client = new RMIClient();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();

  }


  @Test
  void testIfWeGetAllShowings() throws ServerException
  {
    client.startClient();
    viewModel.setSelectedMovie(setup.getMovie());
    assertEquals(setup.getShowingList().size(),viewModel.getAllShowings().size());

  }


  @Test void testIfShowingIsRemoved() throws ServerException
  {

    client.startClient();
    viewModel.setSelectedMovie(setup.getMovie());
    viewModel.removeShowing(setup.getShowing());
    assertEquals(setup.getShowingList().size()-1, viewModel.getAllShowings().size());
  }


}