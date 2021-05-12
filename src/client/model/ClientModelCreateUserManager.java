package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.Movie;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientModelCreateUserManager extends ClientModelManager implements ClientModelCreateUser
{
  public ClientModelCreateUserManager(RMIClient client)
  {
    super(client);
  }

  @Override public void createUser(String userName, String email,
      String password) throws RemoteException, SQLException, ServerException
  {
    super.getClient().createUser(userName, email, password);
  }

}
