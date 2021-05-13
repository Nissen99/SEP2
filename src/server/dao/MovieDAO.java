package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.Movie;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MovieDAO
{
  Movie create(String movieTitle) throws ServerException;
  ArrayList<Movie> getAllMovies() throws ServerException;
  void removeMovie(Movie movie) throws ServerException;
}
