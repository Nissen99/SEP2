package client.model;

import shared.exception.ServerException;
import shared.transferobjects.IMovie;


/**
 * Implementeres af ClientModelMovieManager
 */

public interface ClientModelMovie extends ClientModel
{
  IMovie addMovie(IMovie movie)
      throws ServerException;
  void removeMovie(IMovie movie) throws ServerException;
}
