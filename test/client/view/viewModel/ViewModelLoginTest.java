package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.dao.UserDAO;
import server.dao.UserDAOImpl;
import shared.exception.ServerException;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ViewModelLoginTest
{
  private VMTestSetup setup = new VMTestSetup();
  private ViewModelLogin viewModel = new ViewModelLogin();
  private UserDAO userDAO = new UserDAOImpl();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test void testAdminLogin() {
    viewModel.userNameProperty().setValue("Admin");
    viewModel.passwordProperty().setValue("Admin");
    assertTrue(viewModel.admin());
  }

  @Test void testLogin() throws ServerException
  {
    viewModel.userNameProperty().setValue(setup.getUserName());
    viewModel.passwordProperty().setValue(setup.getPassword());
    viewModel.login();
    assertEquals(setup.getUser().getUserName(), userDAO.getById(1).getUserName());
    assertEquals(setup.getUser().getPassword(), userDAO.getById(1).getPassword());
  }

  @Test void testLoginWithNoUserCreated() {
    viewModel.userNameProperty().setValue("HansHansen");
    viewModel.passwordProperty().setValue("123Hans");
    assertThrows(ServerException.class, () -> {
      viewModel.login();
    });
  }

  @Test void testLoginWithNoInput() {
    assertThrows(ServerException.class, () -> {
      viewModel.login();
    });
  }
}