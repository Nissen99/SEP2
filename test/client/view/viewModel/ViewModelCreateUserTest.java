package client.view.viewModel;

import client.model.ClientModelCreateUser;
import client.model.ClientModelCreateUserManager;
import client.network.RMIClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.dao.UserDAO;
import server.dao.UserDAOImpl;
import shared.exception.ServerException;

import java.rmi.RemoteException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelCreateUserTest
{

  private DAOTestSetup setup = new DAOTestSetup();
  private ViewModelCreateUser viewModel = new ViewModelCreateUser();
  private RMIClient client = new RMIClient();
  private ClientModelCreateUser model = new ClientModelCreateUserManager(client);
  private UserDAO userDAO = new UserDAOImpl();

  @BeforeEach void setup() throws SQLException, ServerException
  {
    setup.setup();
  }

  @Test void testIfUserIsCreated() {
    assertNotNull(setup.getUser());
  }

  @Test void testIfUserIsCreatedWithCorrectInfo() {
    assertEquals(setup.getUserName(), setup.getUser().getUserName());
    assertEquals(setup.getEmail(), setup.getUser().getEmail());
    assertEquals(setup.getPassword(), setup.getUser().getPassword());
  }

  @Test void testIfUserIsCreatedInDatabase()
      throws ServerException, RemoteException, SQLException
  {
    client.startClient();
    model.createUser(setup.getUserName(), setup.getEmail() ,setup.getPassword());
    assertEquals(setup.getUserName(), userDAO.getById(1).getUserName());
  }

}