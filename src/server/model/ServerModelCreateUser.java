package server.model;

import shared.exception.ServerException;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ServerModelCreateUser
{
  void createUser(String userName, String email, String password)
      throws ServerException;

}
