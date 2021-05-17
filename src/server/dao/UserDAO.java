package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.User;
import javax.security.auth.login.LoginException;

public interface UserDAO
{
  User create(String userName, String email,String password)
      throws ServerException;
  User getById(int userId) throws ServerException;
  User login(String userName, String password)
      throws ServerException;
}
