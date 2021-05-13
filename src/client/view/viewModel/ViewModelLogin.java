package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelLogin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.exception.ServerException;
import javax.security.auth.login.LoginException;
import java.rmi.RemoteException;

public class ViewModelLogin
{
  private ClientModelLogin clientModelLogin = ModelFactory.getInstance().getModelLogin();
  private StringProperty userNameProperty = new SimpleStringProperty();
  private StringProperty passwordProperty = new SimpleStringProperty();

  public String getUserNameProperty()
  {
    return userNameProperty.get();
  }

  public StringProperty userNamePropertyProperty()
  {
    return userNameProperty;
  }

  public String getPasswordProperty()
  {
    return passwordProperty.get();
  }

  public StringProperty passwordPropertyProperty()
  {
    return passwordProperty;
  }

  public void login() throws LoginException, RemoteException, ServerException
  {
    clientModelLogin.login(userNameProperty.get(),passwordProperty.get());
  }
}
