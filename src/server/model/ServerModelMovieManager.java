package server.model;

import server.dao.MovieDAO;
import server.dao.MovieDAOImpl;
import shared.transferobjects.Movie;
import java.sql.SQLException;

public class ServerModelMovieManager implements ServerModelMovie
{
  private MovieDAO movieDAO = new MovieDAOImpl();

  @Override public Movie addMovie(Movie movie)
  {

    try
    {
      return movieDAO.create(movie.getMovieTitle());
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public void removeMovie(Movie movie) throws SQLException
  {
    movieDAO.removeMovie(movie);
  }
}
