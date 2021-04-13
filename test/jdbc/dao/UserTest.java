package jdbc.dao;


import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserTest
{
  @Test
  public void testCreate() throws SQLException
  {
    UserDAO user = new UserDAOImpl();
    user.create("Solaiman");
  }


}