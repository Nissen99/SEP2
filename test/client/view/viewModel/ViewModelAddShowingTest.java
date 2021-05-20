package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelAddShowingTest
{
  private ViewModelAddShowing viewModel = new ViewModelAddShowing();
  private VMTestSetup setup = new VMTestSetup();

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

  @Test void testIfItsTheRightHall()
      throws ServerException
  {
    assertEquals(setup.getHall().getHallNo(), viewModel.getHallByNumber("A").getHallNo());
  }

  @Test void testGetChoiceList() throws ServerException
  {
    assertEquals(setup.getHall().getHallNo(), viewModel.getChoiceList().get(0));
  }
}