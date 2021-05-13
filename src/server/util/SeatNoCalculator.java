package server.util;

import java.io.Serializable;

public class SeatNoCalculator implements Serializable
{
  private String hallNo;
  private int currentSeatNo;
  private int maxSeatsInRow;
  private int maxRows;

  public SeatNoCalculator(String hallNo, int maxSeatsInRow, int maxRows) {
    this.maxRows = maxRows;
    this.maxSeatsInRow = maxSeatsInRow;
    this.hallNo = hallNo;
    currentSeatNo = 100;
  }

  /**
   * Vi har valgt at give sæder deres seatNo efter hvilken sal de er i + hvor de
   * er placeret i salen.

   */
  public String calculateSeatNo(){
    currentSeatNo++;

    if (availableSpaceInRow()){
      return hallNo + currentSeatNo;
    }
    else if (lastSpaceAndNotLastRow()){
      int returnNumber = currentSeatNo;
      currentSeatNo = rowSwitch();
      return hallNo + returnNumber;
    }
    else if (lastSpaceInLastRow()){
      return hallNo + currentSeatNo;
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
