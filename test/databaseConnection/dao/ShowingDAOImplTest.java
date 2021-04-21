package databaseConnection.dao;

import org.junit.jupiter.api.Test;
import shared.transferobjects.Hall;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.sql.SQLException;
import java.sql.Timestamp;

class ShowingDAOImplTest
{

@Test
  public void testThatItGetsToDatabase() throws SQLException
{

  ShowingDAO showingDAO = new ShowingDAOImpl();
  MovieDAO movieDAO = new MovieDAOImpl();

  Movie movie = new Movie(1, "Yikes");
  Showing showing = new Showing(1, movie, new Timestamp(23232), new Hall("S", 10, 10));
  showingDAO.create(showing);
}
}