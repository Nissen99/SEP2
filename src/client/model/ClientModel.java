package client.model;

import client.network.Client;
import shared.exception.ServerException;
import shared.transferobjects.*;

import java.util.ArrayList;

/**
 * ClientModel implemeneters af ClientModelManager
 */
public interface ClientModel
{
  ArrayList<IMovie> getMovieList() throws ServerException;
  Client getClient();

}
