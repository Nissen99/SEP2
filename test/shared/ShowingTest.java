package shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.transferobjects.Hall;
import shared.transferobjects.Movie;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ShowingTest
{
  Showing showing;
  Hall hall;
  Movie movie;
  Timestamp rightNow;

  @BeforeEach
  public void setup()
  {
    movie = new Movie(1, "Batman");
    Date date = new Date();
    rightNow = new Timestamp(date.getTime());
    hall = new Hall("A", 5, 5);
    for (int i = 0; i < hall.getMaxRows() * hall.getMaxSeatsInRow(); i++)
    {
      Seat seat = new Seat();
      hall.addSeat(seat);
    }

    showing = new Showing(1, movie, rightNow, hall);
  }

  @Test
  public void constructorSkalOpretteShowing() {
    assertNotNull(showing);
  }

  @Test
  public void harShowingDenRigtigeHall() {
    assertEquals("A", hall.getHallNo());
  }

  @Test
  public void harShowingDenRigtigeMovie() {
    assertEquals("Batman", movie.getMovieTitle());
  }

  @Test
  public void harShowingDenRigtigeId() {
    assertEquals(1, showing.getId());
  }


  @Test
  public void harShowingDenRigtigeTid() {
    assertEquals( (new SimpleDateFormat("HH:mm")).format(rightNow.getTime()), showing.getTime());
  }
}