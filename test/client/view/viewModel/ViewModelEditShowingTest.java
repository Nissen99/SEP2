package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import shared.transferobjects.IShowing;
import shared.transferobjects.Showing;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ViewModelEditShowingTest
{

  private VMTestSetup setup = new VMTestSetup();
  private ViewModelEditShowing viewModel = new ViewModelEditShowing();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
    viewModel.setSelectedMovie(setup.getMovie());
  }

  @Test
  void testIfWeGetAllShowings() throws ServerException
  {
    assertEquals(setup.getShowingList().size(), viewModel.getAllShowings().size());
  }

  @Test void whenShowingRemovedListChanges() throws ServerException
  {
    int listSize = viewModel.getAllShowings().size();
    viewModel.removeShowing(viewModel.getAllShowings().get(0));
    assertEquals(listSize - 1, viewModel.getAllShowings().size());
  }

  @Test void testRightElementIsRemoved() throws ServerException
  {
    IShowing showing = viewModel.getAllShowings().get(0);
    viewModel.removeShowing(showing);
    assertFalse(viewModel.getAllShowings().contains(showing));
  }

  @Test void ifShowingIsNullOnRemove()
  {
    assertThrows(NullPointerException.class, () -> viewModel.removeShowing(null));
  }

  @Test void testIfWeCanSetMovieAsNull(){
    assertThrows(NullPointerException.class, () -> viewModel.setSelectedMovie(null));
  }

  @Test void removeSomethingNotOnList()
  {
    IShowing showing = new Showing(setup.getMovie(),new Timestamp(System.currentTimeMillis() + 3600000), setup.getHall());
    assertThrows(ServerException.class, () ->viewModel.removeShowing(showing));
  }

}