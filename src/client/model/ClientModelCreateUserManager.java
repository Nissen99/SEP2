package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;


/**
 * Denne klasse extender ClientModelManager some har en referance til vores
 * Client, vi bruger super.getClient() når den skal bruges
 *
 * Denne klasse sender videre fra ViewModel Til Client, har ikke i sig selv noget logik
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
