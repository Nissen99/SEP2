package server.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.transferobjects.Hall;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class HallDAOImplTest
{

  private HallDAO hallDAO;

  @BeforeEach
  public void setUp(){
    hallDAO = new HallDAOImpl();

  }

  @Test
  public void hallGetsPutInDatabase() throws SQLException
  {
    Hall hall = setUpWithHallNo("J");
    hallDAO.create(hall);
    Hall hall2 = hallDAO.getHallByNumber("J");
    assertEquals(hall.getHallNo(), hall2.getHallNo());
    assertEquals(hall.getMaxRows(), hall2.getMaxRows());
    assertEquals(hall.getMaxSeatsInRow(), hall2.getMaxSeatsInRow());

  }






  public Hall setUpWithHallNo(String hallNo){
    return new Hall(hallNo, 5, 5);
  }


}