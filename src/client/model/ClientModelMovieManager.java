package client.model;

import client.network.Client;
import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.IMovie;



/**
 * Denne klasse extender ClientModelManager som har en reference til Client.
 * Vi bruger super.getClient når Client skal bruges.
 *
 * Denne klasse videresender data som den får fra ViewModel til Client.
 */

public class ClientModelMovieManager extends ClientModelManager implements ClientModelMovie
{
  public ClientModelMovieManager(Client client)
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
