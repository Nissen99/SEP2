package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.IMovie;
import java.util.ArrayList;

/**
 * Implementeres af MovieDAOImpl
 */
public interface MovieDAO
{
  IMovie create(String movieTitle) throws ServerException;
  ArrayList<IMovie> getAllMovies() throws ServerException;
  void removeMovie(IMovie movie) throws ServerException;
}
