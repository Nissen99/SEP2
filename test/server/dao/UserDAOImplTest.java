package server.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import shared.transferobjects.IUser;
import shared.transferobjects.User;
import static org.junit.jupiter.api.Assertions.*;
/*
  IUser create(String userName, String email,String password)
      throws ServerException;
      IUser getById(int userId) throws ServerException;
      IUser login(String userName, String password)
      throws ServerException;

 */

class UserDAOImplTest
{
  private DAOTestSetup setup = new DAOTestSetup();
  private UserDAO userDAO = new UserDAOImpl();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test
  void testCreate() throws ServerException
  {
    assertEquals(2, userDAO.create("Mikkel", "Sara@Hotmail.com", "JegStalkerIkkeSara").getUserID());
  }

  @Test
  void createUserWithNull() throws ServerException
  {
    assertThrows(ServerException.class, () -> userDAO.create(null, "denneMail@Gmail.com", "Sikkerhed"));
  }

  @Test
  void createUserWithNul() throws ServerException
  {
    assertThrows(ServerException.class, () -> userDAO.create("Yikers", "denneMail@Gmail.com", null));
  }

  @Test
  void createUserWithNu() throws ServerException
  {
    assertThrows(ServerException.class, () -> userDAO.create("denneMail@Gmail.com", null, "Sikkerhed"));
  }

}