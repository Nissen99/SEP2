package DatabaseConnection.dao;


import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class UserTest
{
  @Test
  public void testCreate() throws SQLException
  {
    UserDAO user = new UserDAOImpl();
    user.create("Solaiman");
  }


}