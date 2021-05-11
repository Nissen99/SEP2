package client.model;

import client.network.Client;
import client.network.RMIClient;
import shared.transferobjects.*;

import javax.security.auth.login.LoginException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
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
