package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.IUser;

public interface UserDAO
{
  User create(String userName, String email,String password)
      throws ServerException;
  IUser getById(int userId) throws ServerException;
  IUser login(String userName, String password)
      throws ServerException;
}
