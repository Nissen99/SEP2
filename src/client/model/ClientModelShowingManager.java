package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.Hall;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ClientModelShowingManager extends ClientModelManager implements ClientModelShowing
{
  public ClientModelShowingManager(RMIClient client)
  {
    super(client);
  }

  @Override public ArrayList<Showing> getShowingList(Movie movie)
      throws ServerException
  {

    return super.getClient().getShowingList(movie);
  }

  @Override public Showing addShowing(Showing showing)
      throws ServerException
  {
    return super.getClient().addShowing(showing);
  }

  @Override public Hall getHallByNumber(String hallNo)
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

  @Override public void removeShowing(Showing showing) throws ServerException
  {
    super.getClient().removeShowing(showing);
  }

}
