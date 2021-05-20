package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.dao.ShowingDAO;
import server.dao.ShowingDAOImpl;
import shared.exception.ServerException;
import shared.transferobjects.IShowing;
import shared.transferobjects.Showing;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelShowingListTest
{
  private VMTestSetup setup = new VMTestSetup();
  private ViewModelShowingList viewModel = new ViewModelShowingList();
  private ShowingDAO showingDAO = new ShowingDAOImpl();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
    viewModel.setSelectedMovie(setup.getMovie());
    viewModel.setSelectedShowing(setup.getShowing());
  }

  @Test void testIfWeGetMovieTitle() {
    assertEquals(setup.getMovie().getMovieTitle(), viewModel.getMovieTitle());
  }

  @Test void testIfWeCanSetMovieAsNull(){
    assertThrows(NullPointerException.class, () -> viewModel.setSelectedMovie(null));
  }


  @Test void pastShowingsAreIgnored() throws ServerException
  {
    IShowing showing = showingDAO.create(new Showing(setup.getMovie(), new Timestamp(System.currentTimeMillis() - 360000), setup.getHall()));

    assertFalse(viewModel.getFutureShowings().contains(showing));
  }

  @Test void testGetAllShowings() throws ServerException
  {
    assertEquals(setup.getShowingList().size(), viewModel.getFutureShowings().size());
  }


  @Test void testGetSelectedShowing() {
    assertEquals(setup.getShowing(), viewModel.getSelectedShowing());
  }

  @Test void selectedShowingCanBeNull(){
    assertThrows(NullPointerException.class, () -> viewModel.setSelectedShowing(null));
  }



}