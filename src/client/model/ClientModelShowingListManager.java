package client.model;

import client.network.Client;
import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.IMovie;
import shared.transferobjects.IShowing;
import java.util.ArrayList;

/**
 * Denne klasse extender ClientModelManager some har en reference til Client.
 * Vi bruger super.getClient når Client skal bruges
 *
 * Denne klasse videresender data som den får fra ViewModel til Client.
 */
public class ClientModelShowingListManager extends ClientModelManager implements ClientModelShowingList
{


  public ClientModelShowingListManager(Client client)
  {
    super(client);
  }


  @Override public ArrayList<IShowing> getShowingList(
      IMovie movie)
      throws ServerException
  {
    return super.getClient().getShowingList(movie);
  }
}
