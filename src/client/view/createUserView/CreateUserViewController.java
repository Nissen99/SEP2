package client.view.createUserView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.Controller;
import client.view.viewModel.ViewModelCreateUser;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import shared.exception.ServerException;
/**
 * Controller til createUserView, står for at læse bruger inputs, dette er gjort
 * gennem bindings med viewModellen.
 */

public class CreateUserViewController implements Controller
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

  /**
   * Når man indsætter en user i databasen har vi lavet functioner + triggers der
   * tjekker input, derfor kan vi give relevante fejlmeddelser til brugeren gennem
   * e.getMessage()
   */
  public void onCreateButton()
  {
    try
    {
      viewModelCreateUser.create();
      Alert alert = AlertBox.makeAlert("information", "Bruger oprettet", "Du er nu oprettet som bruger");
      alert.showAndWait();
      ViewHandler.getInstance().openView("Login");
    }
    catch (ServerException | IllegalArgumentException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!", e.getMessage());
      alert.show();
    }
  }

  public void onBackButton()
  {
   ViewHandler.getInstance().openView("Login");
  }
}
