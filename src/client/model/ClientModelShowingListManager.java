package client.model;

import client.network.Client;
import client.network.RMIClient;
import shared.transferobjects.Hall;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ClientModelShowingListManager extends ClientModelManager implements ClientModelShowingList
{


  public ClientModelShowingListManager(RMIClient client)
  {
    super(client);
  }

  public RMIClient getClient(){
    return super.getClient();
  }

  @Override public ArrayList<Showing> getShowingList(Movie movie)
      throws SQLException, RemoteException
  {
    return super.getClient().getShowingList(movie);
  }
}
