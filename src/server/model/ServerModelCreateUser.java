package server.model;

import shared.exception.ServerException;


/**
 * Implementeres af ServeModelCreateUser
 */
public interface ServerModelCreateUser
{
  void createUser(String userName, String email, String password)
      throws ServerException;

}
