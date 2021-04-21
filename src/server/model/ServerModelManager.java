package server.model;

import databaseConnection.dao.*;
import shared.transferobjects.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerModelManager implements ServerModel
{

  private BookingDAO bookingDAO;
  private UserDAO userDAO;
  private MovieDAO movieDAO;
  private ShowingDAO showingDAO;
  private HallDAO hallDAO;

  public ServerModelManager()
  {
    this.bookingDAO = new BookingDAOImpl();
    this.userDAO = new UserDAOImpl();
    this.movieDAO = new MovieDAOImpl();
    this.showingDAO = new ShowingDAOImpl();
    this.hallDAO = new HallDAOImpl();
  }

  @Override public Booking addBooking(Showing showing, String username,
      String seatNo)
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

  @Override public Movie addMovie(Movie movie)
  {

    try
    {
      return movieDAO.create(movie.getMovieTitle());
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
      System.out.println("Catch in addMovie");
    }
    return null;
  }

  @Override public Showing addShowing(Showing showing)
  {

    try
    {
      return showingDAO.create(showing);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
      System.out.println("Catch in addShowing");
    }
    return null;

  }

  @Override public Hall addHall(Hall hall)
  {
    try
    {
      return hallDAO.create(hall);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
      System.out.println("Catch in addHall");
    }
    return null;
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