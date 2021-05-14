package client.view.loginView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.viewModel.ViewModelLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import shared.exception.ServerException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.sql.SQLException;

public class LoginViewController
{
  @FXML private TextField usernameField;
  @FXML private PasswordField passwordField;
  private ViewModelLogin viewModelLogin = ViewModelFactory.getInstance().getlogin();


  public void init()
  {
    usernameField.textProperty().bindBidirectional(viewModelLogin.userNamePropertyProperty());
    passwordField.textProperty().bindBidirectional(viewModelLogin.passwordPropertyProperty());
  }



  @FXML void createAccountButtonPressed()
  {
    ViewHandler.getInstance().openView("../view/createUserView/createUserView.fxml");
  }

  @FXML void loginButtonPressed()
  {
    /**
     * Som vi har lavet det pt tjekker den om der er skrevet "Admin" i begge felter
     * Hvis dette sker kommer du i admin view hvor der kan ændres på film, bookings og
     * showings.
     */
    if (viewModelLogin.admin())
    {
      ViewHandler.getInstance().openView("../view/adminView/adminView.fxml");
    }
    else {
      try
      {
        viewModelLogin.login();
        ViewHandler.getInstance().openView("../view/movieList/movieListView.fxml");
      }
      catch (ServerException e)
      {
        Alert alert = AlertBox.makeAlert("information","Login error ", e.getMessage());
        alert.showAndWait();
      }
    }
  }

}
