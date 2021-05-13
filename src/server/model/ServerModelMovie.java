package server.model;

import shared.exception.ServerException;
import shared.transferobjects.Movie;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ServerModelMovie
{
  Movie addMovie(Movie movie) throws ServerException;
  void removeMovie(Movie movie) throws ServerException;
}
