package client.model;

import shared.exception.ServerException;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ClientModelCreateUser extends ClientModel
{
  void createUser(String userName, String email, String password)
      throws ServerException;
}
