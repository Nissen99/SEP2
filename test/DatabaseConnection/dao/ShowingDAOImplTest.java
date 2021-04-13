package DatabaseConnection.dao;

import org.junit.jupiter.api.Test;
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
  Showing showing = new Showing(55, movie, new Timestamp(23232) );
  showingDAO.create(movie, showing.getTimestamp());
}
}