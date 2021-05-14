package shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.util.SeatNoCalculator;
import shared.transferobjects.*;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest
{
  Showing showing;
  Hall hall;
  Movie movie;
  Timestamp rightNow;
  User user;
  Booking booking;

  @BeforeEach
  public void setUp() {
    movie = new Movie(1, "Batman");
    Date date = new Date();
    rightNow = new Timestamp(date.getTime());
    hall = new Hall("A", 5, 5);
    SeatNoCalculator seatNoCalculator = new SeatNoCalculator(hall.getHallNo(), hall.getMaxSeatsInRow(), hall.getMaxRows());

    for (int i = 0; i < hall.getMaxRows() * hall.getMaxSeatsInRow(); i++)
    {
      Seat seat = new Seat();
      seat.setSeatNo(seatNoCalculator.calculateSeatNo());
      hall.addSeat(seat);
    }

    showing = new Showing(1, movie, rightNow, hall);
    user = new User(1, "Henning","heninsmail@mail.com","pass123");
    booking = new Booking(1, showing, user);
  }

  @Test
  public void constructorSkalOpretteBooking() {
    assertNotNull(booking);
  }

  @Test
  public void consSkalSætteRigtigShowing() {
    assertEquals(showing, booking.getShowing());
  }

  @Test
  public void consSkalSætteRigtigUser() {
    assertEquals("Henning", user.getUserName());
  }


  @Test
  public void consSkalSætteRigtigID () {
    assertEquals(1, booking.getBookingId());
  }



}