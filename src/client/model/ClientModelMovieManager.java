package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.Movie;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class ClientModelMovieManager extends ClientModelManager implements ClientModelMovie
{
  public ClientModelMovieManager(RMIClient client)
  {
    super(client);
  }

  @Override public void removeMovie(Movie movie)
      throws RemoteException, SQLException
  {
    super.getClient().removeMovie(movie);
  }



  @Override public Movie addMovie(Movie movie)
      throws SQLException, RemoteException, ServerException
  {
    return super.getClient().addMovie(movie);
  }
}
