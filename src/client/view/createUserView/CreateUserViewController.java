package client.view.createUserView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.viewModel.ViewModelCreateUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
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

  public void onCreateButton(ActionEvent actionEvent)
      throws IOException, SQLException
  {
   viewModelCreateUser.create();


   ViewHandler.getInstance().openView("../view/loginView/loginView.fxml");


  }



  public void onBackButton(ActionEvent actionEvent)
      throws IOException, SQLException
  {
   ViewHandler.getInstance().openView("../view/loginView/loginView.fxml");

  }
}
