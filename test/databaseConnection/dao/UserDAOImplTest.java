package databaseConnection.dao;


import databaseConnection.dao.UserDAO;
import databaseConnection.dao.UserDAOImpl;
import org.junit.jupiter.api.Test;
import shared.transferobjects.User;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;

class UserDAOImplTest
{
  @Test
  public void testCreate() throws SQLException
  {
    UserDAO userDAO = new UserDAOImpl();
    User user = userDAO.create("Solaiman","solaiman@gmail.com");


    assertEquals("Solaiman", userDAO.getById(user.getUserID()).getName());
  }


}