package databaseConnection.dao;

import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface ShowingDAO
{


  ArrayList<Showing> getAllShowings(Movie movie) throws SQLException;
  Showing create(Showing showing) throws SQLException;
  public ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo, Timestamp timestamp) throws SQLException;
}
