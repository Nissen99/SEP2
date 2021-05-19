package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;


/**
 * Denne klasse extender ClientModelManager som har en reference til Client.
 * Vi bruger super.getClient() når Client skal bruges.
 *
 * Denne klasse videresender data som den får fra ViewModel til Client.
 */

public class ClientModelCreateUserManager extends ClientModelManager implements ClientModelCreateUser
{
  public ClientModelCreateUserManager(RMIClient client)
  {
    super(client);
  }


  @Override public void createUser(String userName, String email,
      String password) throws ServerException
  {
    super.getClient().createUser(userName, email, password);
  }

}
