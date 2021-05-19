package client.view.loginView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.Controller;
import client.view.viewModel.ViewModelLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import shared.exception.ServerException;

/**
 * Controller til loginView, står for at læse bruger inputs, dette er gjort
 * gennem bindings med viewModellen.
 */
public class LoginViewController implements Controller
{
  @FXML private TextField usernameField;
  @FXML private PasswordField passwordField;
  private ViewModelLogin viewModelLogin = ViewModelFactory.getInstance().getlogin();


  public void init()
  {
    usernameField.textProperty().bindBidirectional(viewModelLogin.userNameProperty());
    passwordField.textProperty().bindBidirectional(viewModelLogin.passwordProperty());
  }



  @FXML void createAccountButtonPressed()
  {
    ViewHandler.getInstance().openView("Create User");
  }

  /**
   * Tjekker om der er skrevet "Admin" i begge felter, hvis dette er sandt
   * sendes man til admin view hvor der kan ændres på film, bookings og
   * showings.
   */
  @FXML void loginButtonPressed()
  {
    try
    {
    if (viewModelLogin.admin())
    {
      ViewHandler.getInstance().openView("Admin");
    }
    else {

        viewModelLogin.login();
        ViewHandler.getInstance().openView("Movie List");
      }

    }catch (ServerException e)
    {
      Alert alert = AlertBox.makeAlert("information","Login error ", e.getMessage());
      alert.show();
    }catch (NullPointerException ignored){}
  }

}
