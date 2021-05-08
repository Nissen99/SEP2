package client.model;

import shared.transferobjects.User;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ClientModelCreateUser extends ClientModel
{
  void createUser(String userName, String email, String password) throws RemoteException, SQLException;
}
