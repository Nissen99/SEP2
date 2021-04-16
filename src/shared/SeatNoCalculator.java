package shared;

public class SeatNoCalculator
{
  private int currentSeatNo;
  private int maxSeatsInRow;
  private int maxRows;

  SeatNoCalculator(int maxRows, int maxSeatsInRow) {
    this.maxRows = maxRows;
    this.maxSeatsInRow = maxSeatsInRow;
    currentSeatNo = 100;
  }

  public int calculateSeatNo(){
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
    } else {
      throw new IllegalStateException("Hall full, cant add more seats");
    }


  }
  public int getMaxSeatsInRow()
  {
    return maxSeatsInRow;
  }

  public int getMaxRows()
  {
    return maxRows;
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
