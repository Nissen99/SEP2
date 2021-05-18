package server.model;

import shared.exception.ServerException;
import shared.transferobjects.IUser;

public interface ServerModelLogin
{
  IUser login(String userName,String password) throws ServerException;
}
