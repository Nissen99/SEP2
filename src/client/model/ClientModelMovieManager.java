package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.IMovie;




/**
 * Denne klasse extender ClientModelManager some har en referance til vores
 * Client, vi bruger super.getClient når den skal bruges
 */

public class ClientModelMovieManager extends ClientModelManager implements ClientModelMovie
{
  public ClientModelMovieManager(RMIClient client)
  {
    super(client);
  }

  @Override public void removeMovie(IMovie movie) throws ServerException
  {
    super.getClient().removeMovie(movie);
  }



  @Override public IMovie addMovie(IMovie movie)
      throws ServerException
  {
    return super.getClient().addMovie(movie);
  }
}
