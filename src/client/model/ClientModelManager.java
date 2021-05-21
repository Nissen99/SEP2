package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.*;
import java.util.ArrayList;

/**
 * Denne klasse bliver extended af alle managers. De andre managers
 * kalder på Client igennem denne klasse.
 *
 * Denne klasse videresender data som den får fra ViewModel til Client.
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
