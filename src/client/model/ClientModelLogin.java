package client.model;

import shared.exception.ServerException;



/**
 * Implementeres af ClientModelLoginManager
 */

public interface ClientModelLogin
{
  void login(String userName,String password)
      throws ServerException;
}
