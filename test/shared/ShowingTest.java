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
import java.util.HashMap;

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
  void equalsTjekkerObjekterIInstancen(){
    Showing is = new Showing(1, movie, rightNow, hall);

    assertEquals(showing, is);
  }

  @Test
  void equalsTjekkerOmMovieErEns(){
    Movie notRightMove = new Movie("SpiderMan 3");
    Showing is = new Showing(1, notRightMove, rightNow, hall);

    assertNotEquals(showing, is);
  }

  @Test
  void equalsTjekkerOmTidsPunktErEns(){
    Timestamp notRightTime = new Timestamp(System.currentTimeMillis() + 360000);
    Showing is = new Showing(1, movie , notRightTime, hall);

    assertNotEquals(showing, is);
  }

  @Test
  void equalsTjekkerOmHallErEns(){
    Hall notRightHall = new Hall("T", 5,5);
    Showing is = new Showing(1, movie, rightNow, notRightHall);

    assertNotEquals(showing, is);
  }


  @Test
  void constructorSkalOpretteShowing() {
    assertNotNull(showing);
  }

  @Test
  void harShowingDenRigtigeHall() {
    assertEquals("A", hall.getHallNo());
  }

  @Test
  void harShowingDenRigtigeMovie() {
    assertEquals("Batman", movie.getMovieTitle());
  }

  @Test
  void harShowingDenRigtigeId() {
    assertEquals(1, showing.getId());
  }


  @Test
  void harShowingDenRigtigeTid() {
    assertEquals( (new SimpleDateFormat("HH:mm")).format(rightNow.getTime()), showing.getTime());
  }
}