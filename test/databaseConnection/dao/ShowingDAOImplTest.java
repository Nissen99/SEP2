package databaseConnection.dao;

import org.junit.jupiter.api.Test;
import shared.transferobjects.Hall;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class ShowingDAOImplTest
{

@Test
  public void testThatItGetsToDatabase() throws SQLException
{

  ShowingDAO showingDAO = new ShowingDAOImpl();
  MovieDAO movieDAO = new MovieDAOImpl();
  HallDAO hallDAO = new HallDAOImpl();

  Movie movie = new Movie( "TestShowing");
  Hall hall = new Hall("S", 5, 5);
  hallDAO.create(hall);
  movie = movieDAO.create(movie.getMovieTitle());
  Showing showing = new Showing(1, movie, new Timestamp(23232), hall);
  showingDAO.create(showing);

  ArrayList<Showing> showingArrayList = showingDAO.getAllShowings(movie);

  assertEquals(showingArrayList.get(0).toString(), showing.toString());

}
}