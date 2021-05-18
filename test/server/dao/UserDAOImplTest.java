package server.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import shared.transferobjects.User;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;

class UserDAOImplTest
{
  private DAOTestSetup setup = new DAOTestSetup();
  private UserDAO userDAO = new UserDAOImpl();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test
  public void testCreate() throws ServerException
  {
    assertEquals(setup.getUser().getUserName(), userDAO.create(setup.getUserName(), setup.getEmail(), setup.getPassword()).getUserName());

  }


}