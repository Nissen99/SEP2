package server.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeatNoCalculatorTest
{

  SeatNoCalculator seatNoCalculator;

  @BeforeEach
  void setup(){
    seatNoCalculator = new SeatNoCalculator("T", 5, 6);
  }

  @Test
  public void constructorSetsMaxSeatsInRow(){
    assertEquals(5, seatNoCalculator.getMaxSeatsInRow());
    assertEquals(6, seatNoCalculator.getMaxRows());
  }

  @Test
  void constructorThrowsExceptionWhen0Input(){
    assertThrows(IllegalArgumentException.class, () -> new SeatNoCalculator("T", 0, 0));
  }

  @Test
  void constructorThrowsExceptionWhenHallNoIsNull(){
    assertThrows(IllegalArgumentException.class, () -> new SeatNoCalculator(null, 5, 6));
  }

  @Test
  void calculateSeatNo(){
    assertEquals("T101", seatNoCalculator.calculateSeatNo());
    assertEquals("T102", seatNoCalculator.calculateSeatNo());
    assertEquals("T103", seatNoCalculator.calculateSeatNo());
    assertEquals("T104", seatNoCalculator.calculateSeatNo());
  }

  @Test
  void calculateSeatNoShouldCalculateRowChange(){
    for (int i = 0; i < 4; i++)
    {
      seatNoCalculator.calculateSeatNo();
    }

    assertEquals("T105", seatNoCalculator.calculateSeatNo());
    assertEquals("T201", seatNoCalculator.calculateSeatNo());
  }

  @Test
  void calculateSSeatNoShouldThrowExceptionWhenHallFull(){
    for (int i = 0; i < seatNoCalculator.getMaxRows()*seatNoCalculator.getMaxSeatsInRow(); i++)
    {
      seatNoCalculator.calculateSeatNo();
    }

    assertThrows(IllegalStateException.class, () -> seatNoCalculator.calculateSeatNo());
  }

}