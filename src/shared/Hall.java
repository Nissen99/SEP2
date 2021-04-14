package shared;

import java.util.ArrayList;

public class Hall
{
  private int maxSeatsInRow;
  private int maxRows;
  private int hallNo;
  private int currentSeatNo;
  private ArrayList<Seat> seatArrayList = new ArrayList<>();

  public Hall(int hallNo, int maxSeatsInRow, int maxRows)
  {
    if (maxRows == 0 || maxSeatsInRow == 0){
      throw new IllegalArgumentException("row eller maxSeatInRow er 0");
    }
    this.hallNo = hallNo;
    this.maxSeatsInRow = maxSeatsInRow;
    this.maxRows = maxRows;
    currentSeatNo = 100;
  }

  private int addingSeat(){
    currentSeatNo++;

    if (availableSpaceInRow()){
      return currentSeatNo;
    }
    else if (lastSpaceAndNotLastRow()){
      int returnNumber = currentSeatNo;
      currentSeatNo = rowSwitch();
      return returnNumber;
    }
    else if (lastSpaceInLastRow()){
      return currentSeatNo;
      //next will throw exception
      }else {
          throw new IllegalStateException("Hall full, cant add more seats");
    } }


  public int getMaxRows()
  {
    return maxRows;
  }

  public int getMaxSeatsInRow()
  {
    return maxSeatsInRow;
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
    seat.setSeatNo(addingSeat());
    seatArrayList.add(seat);
  }


  private int rowSwitch()
  {
    return currentSeatNo + 100 - maxSeatsInRow;
  }

  private boolean lastSpaceInLastRow()
  {
    return
        currentSeatNo % 100 == maxSeatsInRow && maxRows == currentSeatNo / 100;
  }

  private boolean lastSpaceAndNotLastRow()
  {
    return
        currentSeatNo % 100 == maxSeatsInRow && maxRows > currentSeatNo / 100;
  }

  private boolean availableSpaceInRow()
  {
    return currentSeatNo % 100 < maxSeatsInRow;
  }

}
