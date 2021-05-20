package client.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.dao.UserDAO;
import server.dao.UserDAOImpl;
import shared.exception.ServerException;

import static org.junit.jupiter.api.Assertions.*;

class ClientModelLoginManagerTest
{
  private ModelTestSetup setup = new ModelTestSetup();
  private ClientModelLogin model = new ClientModelLoginManager(setup.getClient());
  private UserDAO userDAO = new UserDAOImpl();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test void testLogin() throws ServerException
  {
    model.login(setup.getUserName(), setup.getPassword());
    assertEquals(setup.getUser().getUserName(), userDAO.getById(1).getUserName());
    assertEquals(setup.getUser().getPassword(), userDAO.getById(1).getPassword());
  }

  @Test void testLoginWithNoUserCreated() {
    assertThrows(ServerException.class, () -> {
      model.login("HansHansen", "12345Hans");
    });
  }

  @Test void testLoginWithNoInput() {
    assertThrows(ServerException.class, () -> {
      model.login(null, null);
    });
  }

}