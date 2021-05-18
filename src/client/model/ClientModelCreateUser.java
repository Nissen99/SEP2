package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;

/**
 * Implementeret af ClientModelCreateUserManager
 */

public interface ClientModelCreateUser extends ClientModel
{

  void createUser(String userName, String email, String password)
      throws ServerException;
}
