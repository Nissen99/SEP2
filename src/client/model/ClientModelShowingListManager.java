package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.IMovie;
import shared.transferobjects.IShowing;


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

  @Override public ArrayList<IShowing> getShowingList(
      IMovie movie)
      throws ServerException
  {
    return super.getClient().getShowingList(movie);
  }
}
