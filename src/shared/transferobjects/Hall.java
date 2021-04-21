package shared.transferobjects;

import java.io.Serializable;
import java.util.ArrayList;

public class Hall implements Serializable
{
  private String hallNo;
  private SeatNoCalculator seatNoCalculator;
  private ArrayList<Seat> seatArrayList = new ArrayList<>();

  public Hall(String hallNo, int maxSeatsInRow, int maxRows)
  {
    if (maxRows == 0 || maxSeatsInRow == 0){
      throw new IllegalArgumentException("row eller maxSeatInRow er 0");
    }
    seatNoCalculator = new SeatNoCalculator(maxRows, maxSeatsInRow, hallNo);
    this.hallNo = hallNo;
  }

  public int getMaxSeatsInRow()
  {
    return seatNoCalculator.getMaxSeatsInRow();
  }

  public int getMaxRows()
  {
    return seatNoCalculator.getMaxRows();
  }

  public String getHallNo()
  {
    return hallNo;
  }

  public ArrayList<Seat> getSeats()
  {
    return seatArrayList;
  }

  public Seat addSeat(Seat seat)
  {

    seat.setSeatNo(seatNoCalculator.calculateSeatNo());
    seatArrayList.add(seat);
    return seat;
  }

}
