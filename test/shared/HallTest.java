package shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.transferobjects.Hall;
import shared.transferobjects.Seat;

import static org.junit.jupiter.api.Assertions.*;

class HallTest
{

  private Hall hall;


  @BeforeEach
  public void setUp(){
   hall = new Hall("S",10, 6);

  }
  @Test
  public void constrotorSkalOpretteHall(){

    assertNotNull(hall);
  }

  @Test
  public void constrotorSkalSætteMaxSeatsInRow(){

    assertEquals(6, hall.getMaxRows());
    assertEquals(10, hall.getMaxSeatsInRow());
  }

  @Test
  public void constrotorSkalSætteHallNo(){

    assertEquals("S", hall.getHallNo());
  }

  @Test
  public void constrotorSkalThrowNårManSætter0Ind(){

    assertThrows(IllegalArgumentException.class, () -> new Hall("S",0,0));
  }
  @Test
  public void addSeatskalAddeSeatMedSeatNoOgRowNo(){

    Hall hall = new Hall("S", 1,1);

    hall.addSeat(new Seat());

    assertEquals("S101",hall.getSeats().get(0).getSeatNo());

  }

  @Test
  public void addSeatskalAddeSeatMedSeatNoOgRowNoMany(){

    for (int i = 0; 12 > i;i++){
      hall.addSeat(new Seat());
    }
    assertEquals("S201",hall.getSeats().get(10).getSeatNo());
  }


  @Test
  public void getSeatsSkalReturnArrayListAfSeats(){

    hall.addSeat(new Seat());
    hall.addSeat(new Seat());
    assertEquals(2, hall.getSeats().size());
  }

  @Test
  public void derIkkeKanAddesForMangeSeats(){
    Hall hall = new Hall("S", 10, 10);
    for (int i = 0; i < 100; i++)
    {
      hall.addSeat(new Seat());
    }
    assertThrows(IllegalStateException.class, () ->hall.addSeat(new Seat()));
  }


}