package databaseConnection.dao;


import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class UserDAOImplTest
{
  @Test
  public void testCreate() throws SQLException
  {
    UserDAO user = new UserDAOImpl();
    user.create("Solaiman");
  }


}