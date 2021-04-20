package shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.Seat;

import static org.junit.jupiter.api.Assertions.*;

class SeatTest
{

  private Seat seat;

  @BeforeEach
  public void setUp(){
    seat = new Seat();

  }

  @Test
  public void contstruotorSkalSætteIsOccupiedIkkeSeatNo(){
    Seat seat = new Seat();

    assertEquals(false, seat.IsOccupied());
    checkSeatNoZero(seat);
  }


  @Test
  public void setIsOccupiedSkalSetteVariable(){

    seat.setIsOccupiedState(true);

    assertEquals(true, seat.IsOccupied());
  }


  @Test
  public void setSeatNoSkalSætteSeatNoZero(){
    checkSeatNoZero(seat);
  }


  @Test
  public void setSeatNoSkalSætteSeatNoOne(){

    seat.setSeatNo("S"+101);

    assertEquals("S"+101, seat.getSeatNo());

  }

  @Test
  public void setSeatNoSkalSætteSeatNoMany(){
    seat.setSeatNo("S"+225);

    assertEquals("S"+225, seat.getSeatNo());

  }



  private void checkSeatNoZero(Seat seat)
  {
    assertEquals(null, seat.getSeatNo());
  }


}