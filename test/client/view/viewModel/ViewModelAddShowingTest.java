package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.transferobjects.Hall;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelAddShowingTest
{
  private Hall hall = new Hall("B", 5, 5);
  private Showing showing = null;
  private Movie movie = new Movie("Forest Gump");
  Timestamp time = new Timestamp(2021, 05, 14,
  13, 30, 0, 0);

  @BeforeEach void setup() {
    if (showing == null) {
      showing = new Showing(1 ,movie, time, hall);
    }
  }

  @Test void testIfShowingIsCreated() {
    assertNotNull(showing);
  }

  @Test void testIfShowingIsCreatedWithCorrectInfo() {
    assertEquals(1, showing.getId());
    assertEquals("Forest Gump", showing.getMovie().getMovieTitle());
    assertEquals(time, showing.getTimestamp());
    assertEquals("B", showing.getHall().getHallNo());
  }


}