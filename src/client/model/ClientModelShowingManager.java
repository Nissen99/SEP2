package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.IHall;
import shared.transferobjects.IMovie;
import shared.transferobjects.IShowing;

import java.sql.Timestamp;
import java.util.ArrayList;
/**
 * Denne klasse extender ClientModelManager some har en reference til Client.
 * Vi bruger super.getClient når Client skal bruges
 *
 * Denne klasse videresender data som den får fra ViewModel til Client.
 */
public class ClientModelShowingManager extends ClientModelManager implements ClientModelShowing
{
  public ClientModelShowingManager(RMIClient client)
  {
    super(client);
  }

  @Override public ArrayList<IShowing> getShowingList(
      IMovie movie)
      throws ServerException
  {
    return super.getClient().getShowingList(movie);
  }

  @Override public IShowing addShowing(IShowing showing)
      throws ServerException
  {
    return super.getClient().addShowing(showing);
  }

  @Override public IHall getHallByNumber(String hallNo)
      throws ServerException
  {
    return super.getClient().getHallByNumber(hallNo);
  }

  @Override public ArrayList<Timestamp> getShowingTimesByHallNoAndDate(
      String hallNo, Timestamp timestamp)
      throws ServerException
  {
    return super.getClient().getShowingTimesByHallNoAndDate(hallNo, timestamp);
  }

  @Override public ArrayList<String> getHallNumbers()
      throws ServerException
  {
    return super.getClient().getHallNumbers();
  }

  @Override public void removeShowing(IShowing showing) throws ServerException
  {
    super.getClient().removeShowing(showing);
  }

}
