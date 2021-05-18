package client.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.dao.UserDAO;
import server.dao.UserDAOImpl;
import shared.exception.ServerException;

import static org.junit.jupiter.api.Assertions.*;

class ClientModelCreateUserManagerTest
{
  private ModelTestSetup setup = new ModelTestSetup();
  private ClientModelCreateUser model = new ClientModelCreateUserManager(setup.getClient());
  private UserDAO userDAO = new UserDAOImpl();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test void testIfUserIsCreatedInDatabase()
      throws ServerException
  {
    model.createUser(setup.getUserName(), setup.getEmail() ,setup.getPassword());
    assertEquals(setup.getUserName(), userDAO.getById(1).getUserName());
  }
}