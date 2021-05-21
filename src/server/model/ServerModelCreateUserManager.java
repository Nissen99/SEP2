package server.model;


import server.dao.UserDAO;
import server.dao.UserDAOImpl;
import shared.exception.ServerException;


public class ServerModelCreateUserManager implements ServerModelCreateUser
{
  private UserDAO userDAO = new UserDAOImpl();


  @Override public synchronized void createUser(String userName, String email,String password)
      throws ServerException
  {
    userDAO.create(userName,email,password);
  }
}
