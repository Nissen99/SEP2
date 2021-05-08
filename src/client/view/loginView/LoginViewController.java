package client.view.loginView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.viewModel.ViewModelLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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



  @FXML void createAccountButtonPressed(ActionEvent event)
      throws IOException, SQLException
  {

    ViewHandler.getInstance().openView("../view/createUserView/createUserView.fxml");

  }

  @FXML void loginButtonPressed(ActionEvent event)
      throws IOException, SQLException, LoginException
  {
    if (usernameField.getText().equals("Admin") && passwordField.getText().equals("Admin"))
    {
      ViewHandler.getInstance().openView("../view/adminView/adminView.fxml");

    }
    else {
      viewModelLogin.login();
      ViewHandler.getInstance().openView("../view/movieList/movieListView.fxml");

    }
  }


}
