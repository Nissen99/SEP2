package databaseConnection.dao;

import shared.Movie;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MovieDAO
{
  Movie create(String movieTitle) throws SQLException;
  ArrayList<Movie> getAllMovies() throws SQLException;
}
