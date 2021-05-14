package shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.util.SeatNoCalculator;
import shared.transferobjects.Hall;
import shared.transferobjects.Seat;

import static org.junit.jupiter.api.Assertions.*;

class HallTest
{

  private Hall hall;
  private SeatNoCalculator seatNoCalculator;

  @BeforeEach
  public void setUp(){
   hall = new Hall("S",10, 6);
    seatNoCalculator = new SeatNoCalculator(hall.getHallNo(), hall.getMaxSeatsInRow(), hall.getMaxRows());

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
    Seat seat = new Seat();
    seat.setSeatNo(seatNoCalculator.calculateSeatNo());
    hall.addSeat(seat);

    assertEquals("S101",hall.getSeats().get(0).getSeatNo());

  }

  @Test
  public void addSeatskalAddeSeatMedSeatNoOgRowNoMany(){

    for (int i = 0; 12 > i;i++){
      Seat seat = new Seat();
      seat.setSeatNo(seatNoCalculator.calculateSeatNo());
      hall.addSeat(seat);
    }
    assertEquals("S201",hall.getSeats().get(10).getSeatNo());
  }


  @Test
  public void getSeatsSkalReturnArrayListAfSeats(){

    Seat seat = new Seat();
    Seat seat2 = new Seat();

    seat.setSeatNo(seatNoCalculator.calculateSeatNo());
    seat.setSeatNo(seatNoCalculator.calculateSeatNo());

    hall.addSeat(seat);
    hall.addSeat(seat2);

    assertEquals(2, hall.getSeats().size());
  }

  @Test
  public void derIkkeKanAddesForMangeSeats(){
    Hall hall = new Hall("S", 10, 10);
    SeatNoCalculator seatNoCalculator1 = new SeatNoCalculator(hall.getHallNo(), hall.getMaxSeatsInRow(), hall.getMaxRows());
    for (int i = 0; i < 100; i++)
    {
      Seat seat = new Seat();
      seat.setSeatNo(seatNoCalculator1.calculateSeatNo());
      hall.addSeat(seat);
    }

    Seat seat = new Seat();


    assertThrows(IllegalStateException.class, () -> seat.setSeatNo(seatNoCalculator1.calculateSeatNo()));
  }


}