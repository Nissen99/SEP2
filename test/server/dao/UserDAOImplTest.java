package server.dao;


import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import shared.transferobjects.User;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;

class UserDAOImplTest
{
  @Test
  public void testCreate() throws SQLException, ServerException
  {
    User user1 = null;
    UserDAO userDAO = new UserDAOImpl();
    userDAO.create("hans","ole@hotmail.com","pass123");


    assertEquals("Solaiman", userDAO.getById(user1.getUserID()).getUserName());
  }


}