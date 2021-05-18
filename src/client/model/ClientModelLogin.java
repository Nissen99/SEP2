package client.model;

import shared.exception.ServerException;



public interface ClientModelLogin
{
  void login(String userName,String password)
      throws ServerException;
}
