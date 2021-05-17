package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Denne klasse extender ClientModelManager some har en referance til vores
 * Client, vi bruger super.getClient når den skal bruges
 */
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
      throws ServerException
  {
    return super.getClient().getShowingList(movie);
  }
}
