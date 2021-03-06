package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.IUser;

/**
 * Implementeres af UserDAOImpl
 */
public interface UserDAO
{
  IUser create(String userName, String email,String password)
      throws ServerException;
  IUser login(String userName, String password)
      throws ServerException;
}
