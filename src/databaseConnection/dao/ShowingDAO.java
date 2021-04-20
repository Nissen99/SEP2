package databaseConnection.dao;

import shared.Movie;
import shared.Showing;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ShowingDAO
{


  ArrayList<Showing> getAllShowings(Movie movie) throws SQLException;
  Showing create(Showing showing) throws SQLException;
}
