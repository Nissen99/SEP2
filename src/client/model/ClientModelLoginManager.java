package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;


/**
 * Denne klasse extender ClientModelManager som har en reference til Client.
 * Vi bruger super.getClient når Client skal bruges.
 *
 * Denne klasse videresender data som den får fra ViewModel til Client.
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
