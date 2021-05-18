package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.*;

import java.util.ArrayList;

/**
 * Denne klasse bliver extended af alle managers, og er måden de andre managers
 * kalder på client
 */

public class ClientModelManager implements ClientModel
{
  private RMIClient client;


  public ClientModelManager(RMIClient client)
  {
    this.client = client;
      }

  @Override public RMIClient getClient()
  {
    return client;
  }



  @Override public ArrayList<IMovie> getMovieList()
      throws ServerException
  {
    return client.getMovieList();
  }



}
