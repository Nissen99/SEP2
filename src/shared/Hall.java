package shared;

import java.util.ArrayList;

public class Hall
{
  private int hallNo;
  private SeatNoCalculator seatNoCalculator;
  private ArrayList<Seat> seatArrayList = new ArrayList<>();

  public Hall(int hallNo, int maxSeatsInRow, int maxRows)
  {
    if (maxRows == 0 || maxSeatsInRow == 0){
      throw new IllegalArgumentException("row eller maxSeatInRow er 0");
    }
    seatNoCalculator = new SeatNoCalculator(maxRows, maxSeatsInRow);
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

  public int getHallNo()
  {
    return hallNo;
  }

  public ArrayList<Seat> getSeats()
  {
    return seatArrayList;
  }

  public void addSeat(Seat seat)
  {
    seat.setSeatNo(seatNoCalculator.calculateSeatNo());
    seatArrayList.add(seat);
  }

}
