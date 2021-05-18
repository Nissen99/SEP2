package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.*;

import java.util.ArrayList;

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
