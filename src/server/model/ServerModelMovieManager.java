package server.model;

import server.dao.MovieDAO;
import server.dao.MovieDAOImpl;
import shared.exception.ServerException;
import shared.transferobjects.IMovie;

import java.util.ArrayList;

public class ServerModelMovieManager implements ServerModelMovie
{
  private MovieDAO movieDAO = new MovieDAOImpl();

  @Override public IMovie addMovie(IMovie movie) throws ServerException
  {
    return movieDAO.create(movie.getMovieTitle());
  }

  @Override public void removeMovie(IMovie movie) throws ServerException
  {
    movieDAO.removeMovie(movie);
  }

  @Override public ArrayList<IMovie> getMovieList() throws ServerException
  {
    return movieDAO.getAllMovies();
  }
}
