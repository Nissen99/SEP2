package server.model;

import shared.exception.ServerException;
import shared.transferobjects.IMovie;

public interface ServerModelMovie
{
  IMovie addMovie(IMovie movie) throws ServerException;
  void removeMovie(IMovie movie) throws ServerException;
}
