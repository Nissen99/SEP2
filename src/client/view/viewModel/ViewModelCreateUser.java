package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModel;
import client.model.ClientModelCreateUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewModelCreateUser
{


  private StringProperty userNameField = new SimpleStringProperty();
  private StringProperty emailField = new SimpleStringProperty();
  private StringProperty passwordField = new SimpleStringProperty();
  private ClientModelCreateUser clientModel = ModelFactory.getInstance().getModelCreateUser();


  public String getEmailField()
  {
    return emailField.get();
  }

  public StringProperty emailFieldProperty()
  {
    return emailField;
  }

  public String getPasswordField()
  {
    return passwordField.get();
  }

  public StringProperty passwordFieldProperty()
  {
    return passwordField;
  }

  public String getUserNameField()
  {
    return userNameField.get();
  }

  public StringProperty userNameFieldProperty()
  {
    return userNameField;
  }

  public void create() throws RemoteException, SQLException
  {

    clientModel.createUser(userNameField.get(),emailField.get(),passwordField.get());
  }
}
