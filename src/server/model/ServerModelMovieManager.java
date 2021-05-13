package server.model;

import server.dao.MovieDAO;
import server.dao.MovieDAOImpl;
import shared.exception.ServerException;
import shared.transferobjects.Movie;

import java.sql.SQLException;

public class ServerModelMovieManager implements ServerModelMovie
{
  private MovieDAO movieDAO = new MovieDAOImpl();

  @Override public Movie addMovie(Movie movie) throws ServerException
  {
    return movieDAO.create(movie.getMovieTitle());
  }

  @Override public void removeMovie(Movie movie) throws ServerException
  {
    movieDAO.removeMovie(movie);
  }
}
