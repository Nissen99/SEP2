package server.model;


import server.dao.UserDAO;
import server.dao.UserDAOImpl;
import shared.exception.ServerException;

import java.sql.SQLException;

public class ServerModelCreateUserManager implements ServerModelCreateUser
{
  private UserDAO userDAO = new UserDAOImpl();

  @Override public void createUser(String userName, String email,String password)
      throws ServerException
  {
    userDAO.create(userName,email,password);
  }
}