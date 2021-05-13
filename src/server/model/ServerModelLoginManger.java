package server.model;

import server.dao.UserDAO;
import server.dao.UserDAOImpl;
import shared.transferobjects.User;

import javax.security.auth.login.LoginException;
import java.rmi.RemoteException;

public class ServerModelLoginManger implements ServerModelLogin
{
  UserDAO userDAO = new UserDAOImpl();

  @Override public User login(String userName, String password)
      throws LoginException
  {
    return userDAO.login(userName,password);
  }
}
