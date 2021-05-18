package server.dao;

import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import shared.transferobjects.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class ShowingDAOImplTest
{

@Test
  public void testThatItGetsToDatabase() throws SQLException, ServerException
{

  ShowingDAO showingDAO = new ShowingDAOImpl();
  MovieDAO movieDAO = new MovieDAOImpl();
  HallDAO hallDAO = new HallDAOImpl();

  IMovie movie = new Movie( "TestShowing");
  IHall hall = new Hall("S", 5, 5);
  hallDAO.create(hall);
  movie = movieDAO.create(movie.getMovieTitle());
  IShowing showing = new Showing(1, (Movie)movie, new Timestamp(23232), (Hall)hall);
  showingDAO.create(showing);

  ArrayList<IShowing> showingArrayList = showingDAO.getAllShowings(movie);

  assertEquals(showingArrayList.get(0).toString(), showing.toString());

}
}