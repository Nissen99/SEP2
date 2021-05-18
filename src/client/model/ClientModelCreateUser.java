package client.model;

import shared.exception.ServerException;



public interface ClientModelCreateUser extends ClientModel
{
  void createUser(String userName, String email, String password)
      throws ServerException;
}
