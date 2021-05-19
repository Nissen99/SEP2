package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelCreateUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.exception.ServerException;

/**
 * ViewModel for createUserView, her håndteres den læste data fra controlleren,
 * dette sker gennem bindings på forskellige Properties.
 */

public class ViewModelCreateUser
{
  private StringProperty userNameField = new SimpleStringProperty("");
  private StringProperty emailField = new SimpleStringProperty("");
  private StringProperty passwordField = new SimpleStringProperty("");
  private ClientModelCreateUser clientModel = ModelFactory.getInstance().getModelCreateUser();

  public StringProperty emailFieldProperty()
  {
    return emailField;
  }

  public StringProperty passwordFieldProperty()
  {
    return passwordField;
  }

  public StringProperty userNameFieldProperty()
  {
    return userNameField;
  }

  /**
   * Tjeks om valid input lavet på databasen
   * @throws ServerException Ugyldigt input ifølge databasen, eller connection problem
   * @throws IllegalArgumentException Hvis nogle af felterne er tomme
   */
  public void create() throws ServerException, IllegalArgumentException
  {
    if (userNameField.get().isEmpty() || emailField.get().isEmpty() || passwordField.get().isEmpty()){
      throw new IllegalArgumentException("Ugyldigt input - alle felter skal udfyldes");
    }
    clientModel.createUser(userNameField.get(), emailField.get(), passwordField.get());
  }
}
