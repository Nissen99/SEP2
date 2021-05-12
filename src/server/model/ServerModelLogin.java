package server.model;

import shared.transferobjects.User;

import javax.security.auth.login.LoginException;
import java.rmi.RemoteException;

public interface ServerModelLogin
{
  User login(String userName,String password)
      throws LoginException, RemoteException;
}
