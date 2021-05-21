package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ViewModelLoginTest
{
  private VMTestSetup setup = new VMTestSetup();
  private ViewModelLogin viewModel = new ViewModelLogin();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test void testAdminLogin() {
    viewModel.userNameProperty().setValue("Admin");
    viewModel.passwordProperty().setValue("Admin");
    assertTrue(viewModel.admin());
  }

  @Test void testLogin()
  {
    viewModel.userNameProperty().setValue(setup.getUserName());
    viewModel.passwordProperty().setValue(setup.getPassword());
    assertDoesNotThrow( () -> viewModel.login());

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