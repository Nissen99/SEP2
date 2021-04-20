package client.model;

import databaseConnection.dao.*;
import shared.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManager implements Model
{

  private BookingDAO bookingDAO;
  private UserDAO userDAO;
  private MovieDAO movieDAO;
  private ShowingDAO showingDAO;
  private HallDAO hallDAO;

  public ModelManager()
  {
     this.bookingDAO = new BookingDAOImpl();
    this.userDAO = new UserDAOImpl();
    this.movieDAO = new MovieDAOImpl();
    this.showingDAO = new ShowingDAOImpl();
    this.hallDAO = new HallDAOImpl();
  }

  @Override public Booking addBooking(Showing showing, String username,
      String seatNo) throws SQLException
  {

    User user = null;
    try
    {
      user = userDAO.create(username);
      return bookingDAO.create(showing, user, seatNo);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      System.out.println("Catch In addBooking");
        userDAO.deleteUser(user);
    }

    return null;
  }

  @Override public Movie addMovie(Movie movie) throws SQLException
  {
  return movieDAO.create(movie.getMovieTitle());
  }

  @Override public Showing addShowing(Showing showing) throws SQLException
  {

    return showingDAO.create(showing);

  }

  @Override public Hall addHall(Hall hall) throws SQLException
  {
    return hallDAO.create(hall);
  }

  @Override public ArrayList<Movie> getMovieList() throws SQLException
  {
      return movieDAO.getAllMovies();

  }

  @Override public ArrayList<Showing> getShowingList(Movie movie) throws SQLException
  {

    return showingDAO.getAllShowings(movie);
  }


  @Override public ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws SQLException
  {
    return bookingDAO.getOccupiedSeats(showing);
  }




}
