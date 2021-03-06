package client.view.viewModel;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.dao.ResetDAO;
import server.dao.ResetDAOImpl;
import shared.exception.ServerException;
import static org.junit.jupiter.api.Assertions.*;

class ViewModelCreateUserTest
{

  private VMTestSetup setup = new VMTestSetup();
  private ViewModelCreateUser viewModel = new ViewModelCreateUser();
  private ResetDAO resetDAO = new ResetDAOImpl();

  @BeforeEach void setup()
  {
    resetDAO.reset();
  }

  @Test void testIfUserIsCreatedWithCorrectInfo()
  {
    assertEquals(setup.getUserName(), setup.getUser().getUserName());
    assertEquals(setup.getEmail(), setup.getUser().getEmail());
    assertEquals(setup.getPassword(), setup.getUser().getPassword());
  }

  @Test void testCreateUser()
  {

    viewModel.userNameFieldProperty().setValue(setup.getUserName());
    viewModel.passwordFieldProperty().setValue(setup.getPassword());
    viewModel.emailFieldProperty().setValue(setup.getEmail());
    assertDoesNotThrow(() -> viewModel.create());
  }

  @Test void testCreateUserWithoutUsername()
  {
    viewModel.passwordFieldProperty().setValue(setup.getPassword());
    viewModel.emailFieldProperty().setValue(setup.getEmail());
    assertThrows(IllegalArgumentException.class, () -> {
      viewModel.create();
    });
  }

  @Test void testCreateUserWithoutPassword()
  {
    viewModel.userNameFieldProperty().setValue(setup.getUserName());
    viewModel.emailFieldProperty().setValue(setup.getEmail());
    assertThrows(IllegalArgumentException.class, () -> {
      viewModel.create();
    });
  }

  @Test void testCreateUserWithoutEmail()
  {
    viewModel.userNameFieldProperty().setValue(setup.getUserName());
    viewModel.passwordFieldProperty().setValue(setup.getPassword());
    assertThrows(IllegalArgumentException.class, () -> {
      viewModel.create();
    });
  }

  @Test void testCreateUserWithSameUsername() throws ServerException
  {
    viewModel.userNameFieldProperty().setValue(setup.getUserName());
    viewModel.passwordFieldProperty().setValue(setup.getPassword());
    viewModel.emailFieldProperty().setValue(setup.getEmail());
    viewModel.create();
    assertThrows(ServerException.class, () -> {
      viewModel.create();
    });
  }

  @Test void testTooShortPassword() {
    viewModel.userNameFieldProperty().setValue(setup.getUserName());
    viewModel.passwordFieldProperty().setValue("S1b");
    viewModel.emailFieldProperty().setValue(setup.getEmail());
    assertThrows(ServerException.class, () -> {
      viewModel.create();
    });
  }

  @Test void testTooLongPassword() {
    viewModel.userNameFieldProperty().setValue(setup.getUserName());
    viewModel.passwordFieldProperty().setValue("123456789QWERTYUIOP??asdfghjklzxcvbnm123456789asdfghjkl123456789");
    viewModel.emailFieldProperty().setValue(setup.getEmail());
    assertThrows(ServerException.class, () -> {
      viewModel.create();
    });
  }

  @Test void testInvalidPassword() {
    viewModel.userNameFieldProperty().setValue(setup.getUserName());
    viewModel.passwordFieldProperty().setValue("hanshansen");
    viewModel.emailFieldProperty().setValue(setup.getEmail());
    assertThrows(ServerException.class, () -> {
      viewModel.create();
    });
    viewModel.passwordFieldProperty().setValue("HANSHANSEN");
    assertThrows(ServerException.class, () -> {
      viewModel.create();
    });
  }
}