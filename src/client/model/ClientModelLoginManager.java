package client.model;

import client.network.RMIClient;

import javax.security.auth.login.LoginException;
import java.rmi.RemoteException;

public class ClientModelLoginManager extends ClientModelManager implements ClientModelLogin
{
  public ClientModelLoginManager(RMIClient client)
  {
    super(client);
  }

  @Override public void login(String userName, String password)
      throws LoginException, RemoteException
  {
    super.getClient().login(userName,password);
  }
}
