package jdbc.dao;

import org.junit.jupiter.api.Test;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;
import shared.transferobjects.User;

import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ShowingDAOImplTest
{

@Test
  public void testThatItGetsToDatabase() throws SQLException
{

  ShowingDAO showingDAO = new ShowingDAOImpl();
  MovieDAO movieDAO = new MovieDAOImpl();

  Movie movie = new Movie(1, "Yikes");
  Showing showing = new Showing(movie, new Timestamp(23232), 55);
  showingDAO.create(movie, showing.getTimestamp());
}
}