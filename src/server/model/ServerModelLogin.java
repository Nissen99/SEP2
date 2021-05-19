package server.model;

import shared.exception.ServerException;
import shared.transferobjects.IUser;

/**
 * Implementeres af ServerModelLoginManager
 */
public interface ServerModelLogin
{
  IUser login(String userName,String password) throws ServerException;
}
