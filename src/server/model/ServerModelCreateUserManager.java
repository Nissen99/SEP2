package server.model;


import server.dao.UserDAO;
import server.dao.UserDAOImpl;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ServerModelCreateUserManager implements ServerModelCreateUser
{
  private UserDAO userDAO = new UserDAOImpl();

  @Override public void createUser(String userName, String email,String password) throws SQLException
  {
    userDAO.create(userName,email,password);
  }
}
