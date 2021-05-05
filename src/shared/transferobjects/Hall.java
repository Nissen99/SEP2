package shared.transferobjects;

import java.io.Serializable;
import java.util.ArrayList;

public class Hall implements Serializable
{
  private String hallNo;
  private ArrayList<Seat> seatArrayList = new ArrayList<>();
  private int maxSeatsInRow;
  private int maxRows;

  public Hall(String hallNo, int maxSeatsInRow, int maxRows)
  {

    if (maxRows == 0 || maxSeatsInRow == 0){
      throw new IllegalArgumentException("row eller maxSeatInRow er 0");
    }
    this.hallNo = hallNo;
    this.maxSeatsInRow = maxSeatsInRow;
    this.maxRows = maxRows;
  }

  public int getMaxSeatsInRow()
  {
    return maxSeatsInRow;
  }

  public int getMaxRows()
  {
    return maxRows;
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
    seatArrayList.add(seat);
    return seat;
  }

}
