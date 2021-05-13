package client.model;

import client.network.RMIClient;
import shared.transferobjects.*;
import java.rmi.RemoteException;
import java.sql.SQLException;
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



  @Override public ArrayList<Movie> getMovieList()
      throws RemoteException, SQLException
  {
    return client.getMovieList();
  }



}
