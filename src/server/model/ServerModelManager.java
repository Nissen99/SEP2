package server.model;

import server.dao.*;
import shared.exception.ServerException;
import shared.transferobjects.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerModelManager implements ServerModel
{

  private MovieDAO movieDAO;

  public ServerModelManager()
  {
    this.movieDAO = new MovieDAOImpl();
  }

  @Override public ArrayList<Movie> getMovieList() throws ServerException
  {
    return movieDAO.getAllMovies();

  }
}