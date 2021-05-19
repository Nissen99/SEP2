package shared.transferobjects;

import java.util.ArrayList;

public class Hall implements IHall
{
  private String hallNo;
  private ArrayList<ISeat> seatArrayList = new ArrayList<>();
  private int maxSeatsInRow;
  private int maxRows;

  public Hall(String hallNo, int maxSeatsInRow, int maxRows) throws IllegalArgumentException
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

  public ArrayList<ISeat> getSeats()
  {
    return seatArrayList;
  }

  public ISeat addSeat(ISeat seat)
  {
    seatArrayList.add(seat);
    return seat;
  }


}
