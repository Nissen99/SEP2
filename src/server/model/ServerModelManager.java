package server.model;

import server.dao.*;
import shared.exception.ServerException;
import server.mail.FileHandler;
import server.mail.JavaMailUtil;
import shared.ENUM;
import shared.transferobjects.*;

import javax.mail.MessagingException;
import javax.security.auth.login.LoginException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ServerModelManager implements ServerModel
{

  private BookingDAO bookingDAO;
  private UserDAO userDAO;
  private MovieDAO movieDAO;
  private ShowingDAO showingDAO;
  private HallDAO hallDAO;
  private BookingSpecDAO bookingSpecDAO;
  private PropertyChangeSupport propertyChangeSupport;

  public ServerModelManager()
  {
    this.bookingDAO = new BookingDAOImpl();
    this.userDAO = new UserDAOImpl();
    this.movieDAO = new MovieDAOImpl();
    this.showingDAO = new ShowingDAOImpl();
    this.hallDAO = new HallDAOImpl();
    this.bookingSpecDAO = new BookingSpecDAOImpl();
    propertyChangeSupport = new PropertyChangeSupport(this);
  }

  @Override public Booking addBooking(Showing showing, User user,
      ArrayList<Seat> seats) throws ServerException
  {
    Booking booking;
    try
    {
      booking = bookingDAO.create(showing,user);
      for (Seat seat : seats)
      {
        bookingSpecDAO.create(booking, seat);
      }
      FileHandler fileHandler = new FileHandler();
      fileHandler.createPDF(booking,seats);
      JavaMailUtil.sendMail(user.getEmail());
    }
    catch (SQLException | MessagingException | IOException e)
    {
      e.printStackTrace();
      System.out.println("Catch In addBooking");
      userDAO.deleteUser(user);
      throw new ServerException();
    }
    propertyChangeSupport.firePropertyChange(String.valueOf(ENUM.ADDBOOKING), null, "booking");
    System.out.println("FIRE 1");


    return null;
  }

  @Override public void removeBooking(Booking booking) throws SQLException
  {
    bookingDAO.removeBooking(booking);
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

  @Override public ArrayList<Booking> getBookingList() throws SQLException
  {
    return bookingDAO.getAllBookings();
  }

  @Override public ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws SQLException
  {
    return bookingDAO.getOccupiedSeats(showing);
  }

  @Override public void removeMovie(Movie movie) throws SQLException
  {
    movieDAO.removeMovie(movie);
  }

  @Override public Hall getHallByNumber(String hallNo) throws SQLException
  {
    return hallDAO.getHallByNumber(hallNo);
  }

  @Override public ArrayList<Timestamp> getShowingTimesByHallNoAndDate(
      String hallNo, Timestamp timestamp) throws SQLException
  {
    return showingDAO.getShowingTimesByHallNoAndDate(hallNo, timestamp);
  }

  @Override public ArrayList<String> getHallNumbers() throws SQLException
  {
    return hallDAO.getHallNumbers();
  }

  @Override public void createUser(String userName, String email,String password) throws SQLException
  {
    userDAO.create(userName,email,password);
  }

  @Override public User login(String userName, String password)
      throws LoginException
  {
    return userDAO.login(userName,password);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override public void addPropertyChangeListener(String eventName,
      PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(eventName, listener);

  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String eventName,
      PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(eventName, listener);

  }
}