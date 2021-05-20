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
    if (!movie.getMovieTitle().equals("") && (movie.getMovieTitle().charAt(0) != ' ') )
    {
      return movieDAO.create(movie.getMovieTitle());
    }
    else {
      throw new ServerException("Ugyldig Title");
    }
  }

  @Override public void removeMovie(IMovie movie) throws ServerException
  {
    if (!movieDAO.getAllMovies().contains(movie)){
    throw new ServerException("That Showing does not exist");
  }
    movieDAO.removeMovie(movie);
  }

  @Override public ArrayList<IMovie> getMovieList() throws ServerException
  {
    return movieDAO.getAllMovies();
  }
}
