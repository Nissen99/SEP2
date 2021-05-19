package server.model;

import shared.exception.ServerException;
import shared.transferobjects.IMovie;

import java.util.ArrayList;

public interface ServerModelMovie
{
  IMovie addMovie(IMovie movie) throws ServerException;
  void removeMovie(IMovie movie) throws ServerException;
  ArrayList<IMovie> getMovieList() throws ServerException;

}
