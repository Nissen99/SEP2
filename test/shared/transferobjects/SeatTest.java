package shared.transferobjects;

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

    assertEquals(false, seat.getIsOccupied());
    checkSeatNoZero(seat);
  }


  @Test
  public void setIsOccupiedSkalSetteVariable(){

    seat.setIsOccupiedState(true);

    assertEquals(true, seat.getIsOccupied());
  }


  @Test
  public void setSeatNoSkalSætteSeatNoZero(){
    seat.setSeatNo(0);

    checkSeatNoZero(seat);
  }


  @Test
  public void setSeatNoSkalSætteSeatNoOne(){

    seat.setSeatNo(101);

    assertEquals(1, seat.getRow());
    assertEquals(1, seat.getCol());
  }

  @Test
  public void setSeatNoSkalSætteSeatNoMany(){
    seat.setSeatNo(225);

    assertEquals(2, seat.getRow());
    assertEquals(25, seat.getCol());
  }



  private void checkSeatNoZero(Seat seat)
  {
    assertEquals(0, seat.getCol());
    assertEquals(0, seat.getRow());
  }


}