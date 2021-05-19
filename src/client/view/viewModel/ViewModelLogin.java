package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelLogin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.exception.ServerException;

/**
 * ViewModel for login, her håndteres den læste data fra controlleren,
 * dette sker gennem bindings på forskellige Properties.
 */

public class ViewModelLogin
{
  private ClientModelLogin clientModelLogin = ModelFactory.getInstance().getModelLogin();
  private StringProperty userNameProperty = new SimpleStringProperty();
  private StringProperty passwordProperty = new SimpleStringProperty();


  public StringProperty userNameProperty()
  {
    return userNameProperty;
  }

  public StringProperty passwordProperty()
  {
    return passwordProperty;
  }

  public void login() throws ServerException
  {
    clientModelLogin.login(userNameProperty.get(),passwordProperty.get());
  }

  /**
   * Her tjekker vi om input fra brugeren er username="Admin" og password="Admin"
   *
   * @return Om login er admin
   */
  public boolean admin()
  {
    return userNameProperty.get().equals("Admin") && passwordProperty.get().equals("Admin");
  }
}
