package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelCreateUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.exception.ServerException;

import java.rmi.RemoteException;
import java.sql.SQLException;

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

  public void create() throws ServerException
  {
    if (userNameField.get().isEmpty() || emailField.get().isEmpty() || passwordField.get().isEmpty()){
      throw new IllegalArgumentException("Invalid input");
    }
    clientModel.createUser(userNameField.get(),emailField.get(),passwordField.get());
  }
}
