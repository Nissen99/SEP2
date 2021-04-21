package databaseConnection.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.Hall;
import shared.Seat;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SeatDAOImplTest
{

  private SeatDAO seatDAO;

  @BeforeEach
  public void setUp(){
    seatDAO = new SeatDAOImpl();

  }

  @Test
  public void createSeatSeatInDatabase() throws SQLException
  {

    Hall u = hallCreater("U");

    Seat seat = new Seat();
    seat.setSeatNo("U104");


    seatDAO.create(seat, u);

    assertEquals(seat.getSeatNo(), seatDAO.getSeatBySeatNo("U104").getSeatNo());
  }

  public Hall hallCreater(String hallNo) throws SQLException
  {
    Hall hall = new Hall(hallNo, 5, 5);
    HallDAOImpl hallDAO = new HallDAOImpl();
    return hallDAO.create(hall);
  }

}