package client.model;

import javax.security.auth.login.LoginException;
import java.rmi.RemoteException;

public interface ClientModelLogin
{
  void login(String userName,String password)
      throws LoginException, RemoteException;
}
