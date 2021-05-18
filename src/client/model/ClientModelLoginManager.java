package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;


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
