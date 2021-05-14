package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelLogin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.exception.ServerException;


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

  public void login() throws ServerException
  {
    clientModelLogin.login(userNameProperty.get(),passwordProperty.get());
  }

  public boolean admin()
  {
    return getUserNameProperty().equals("Admin") && getPasswordProperty().equals("Admin");
  }
}
