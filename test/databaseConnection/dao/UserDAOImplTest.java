package databaseConnection.dao;


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
    User user = userDAO.create("Solaiman");


    assertEquals("Solaiman", userDAO.getById(user.getUserID()).getName());
  }


}