package server.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import shared.transferobjects.Hall;
import shared.transferobjects.IHall;
import shared.transferobjects.ISeat;
import shared.transferobjects.Seat;

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
  public void createSeatSeatInDatabase() throws SQLException, ServerException
  {

    IHall u = hallCreater("U");

    ISeat seat = new Seat();
    seat.setSeatNo("U104");


    seatDAO.create(seat, u);

    assertEquals(seat.getSeatNo(), seatDAO.getSeatBySeatNo("U104").getSeatNo());
  }

  public IHall hallCreater(String hallNo) throws SQLException, ServerException
  {
    IHall hall = new Hall(hallNo, 5, 5);
    HallDAOImpl hallDAO = new HallDAOImpl();
    return hallDAO.create(hall);
  }

}