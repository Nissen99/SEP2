package shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.util.SeatNoCalculator;
import shared.transferobjects.*;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

class BookingTest
{
  private IShowing showing;
  private IHall hall;
  private IMovie movie;
  private Timestamp rightNow;
  private IUser user;
  private IBooking booking;

  @BeforeEach
  public void setUp() {
    movie = new Movie(1, "Batman");
    rightNow = new Timestamp(System.currentTimeMillis());
    hall = new Hall("A", 5, 5);
    SeatNoCalculator seatNoCalculator = new SeatNoCalculator(hall.getHallNo(), hall.getMaxSeatsInRow(), hall.getMaxRows());

    for (int i = 0; i < hall.getMaxRows() * hall.getMaxSeatsInRow(); i++)
    {
      ISeat seat = new Seat();
      seat.setSeatNo(seatNoCalculator.calculateSeatNo());
      hall.addSeat(seat);
    }

    showing = new Showing(1, movie, rightNow, hall);
    user = new User(1, "Henning","heninsmail@mail.com","Pass123");
    booking = new Booking(1, showing, user);
  }

  @Test
  void bookingEqualsKiggerPåObjekterne(){
    Booking is = new Booking(1, showing, user);

    assertEquals(booking, is);
  }

  @Test
  void bookingEqualsObjekterIkkeEns(){
    User henningsbror = new User(1, "HenningsBror", "IkkeHenning@Hotmail.com", "legitPassword");
    Booking is = new Booking(1, showing, henningsbror);
    assertNotEquals(booking, is);

    Showing showing = new Showing(1, movie, new Timestamp(System.currentTimeMillis() + 360000), hall);
    Booking is2 = new Booking(1, showing, user);

    assertNotEquals(booking, is2);
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
    assertEquals(booking.getUser(), user);
  }


  @Test
  public void consSkalSætteRigtigID () {
    assertEquals(1, booking.getBookingId());
  }



}