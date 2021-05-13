package client.view.createUserView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.viewModel.ViewModelCreateUser;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import shared.exception.ServerException;
import java.io.IOException;
import java.sql.SQLException;

public class CreateUserViewController
{

  @FXML public TextField usernameField;
  @FXML public TextField emailField;
  @FXML public PasswordField passwordField;
  private ViewModelCreateUser viewModelCreateUser = ViewModelFactory.getInstance().getCreateUser();



  public void init()
  {
   usernameField.textProperty().bindBidirectional(viewModelCreateUser.userNameFieldProperty());
   emailField.textProperty().bindBidirectional(viewModelCreateUser.emailFieldProperty());
   passwordField.textProperty().bindBidirectional(viewModelCreateUser.passwordFieldProperty());

  }

  public void onCreateButton()
      throws IOException, SQLException, ServerException
  {
   viewModelCreateUser.create();


   ViewHandler.getInstance().openView("../view/loginView/loginView.fxml");


  }



  public void onBackButton()
      throws IOException, SQLException, ServerException
  {
   ViewHandler.getInstance().openView("../view/loginView/loginView.fxml");

  }
}
