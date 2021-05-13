package client.model;

import shared.exception.ServerException;
import shared.transferobjects.Movie;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ClientModelMovie extends ClientModel
{
  Movie addMovie(Movie movie)
      throws ServerException;
  void removeMovie(Movie movie) throws ServerException;
}
