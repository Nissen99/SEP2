package server.model;

import server.dao.UserDAO;
import server.dao.UserDAOImpl;
import shared.exception.ServerException;
import shared.transferobjects.User;

public class ServerModelLoginManger implements ServerModelLogin
{
  UserDAO userDAO = new UserDAOImpl();

  @Override public User login(String userName, String password)
      throws ServerException
  {
    return userDAO.login(userName,password);
  }
}
