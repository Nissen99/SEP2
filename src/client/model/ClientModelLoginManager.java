package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;

import javax.security.auth.login.LoginException;
import java.rmi.RemoteException;

/**
 * Denne klasse extender ClientModelManager some har en referance til vores
 * Client, vi bruger super.getClient når den skal bruges
 *
 */

public class ClientModelLoginManager extends ClientModelManager implements ClientModelLogin
{
  public ClientModelLoginManager(RMIClient client)
  {
    super(client);
  }

  @Override public void login(String userName, String password)
      throws ServerException
  {
    super.getClient().login(userName,password);
  }
}
