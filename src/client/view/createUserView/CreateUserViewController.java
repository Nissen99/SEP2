package client.view.createUserView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.viewModel.ViewModelCreateUser;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import shared.exception.ServerException;


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
  {
    try
    {
      viewModelCreateUser.create();
      ViewHandler.getInstance().openView("../view/loginView/loginView.fxml");
    }
    catch (ServerException | IllegalArgumentException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!", e.getMessage());
      alert.show();
    }
  }

  public void onBackButton()
  {
   ViewHandler.getInstance().openView("../view/loginView/loginView.fxml");
  }
}
