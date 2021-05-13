package client.view.viewModel;

import server.dao.*;
import shared.exception.ServerException;
import shared.transferobjects.*;

import java.sql.SQLException;
import java.sql.Timestamp;

public class DAOTestSetup
{
  private HallDAOImpl hallDAO;
  private MovieDAO movieDAO = new MovieDAOImpl();
  private ShowingDAO showingDAO = new ShowingDAOImpl();
  private UserDAO userDAO = new UserDAOImpl();
  private BookingDAO bookingDAO = new BookingDAOImpl();
  private ResetDAO resetDAO = new ResetDAO();
  private Hall hall = new Hall("A", 5, 5);
  private Showing showing = null;
  private String movieTitle = "Jackass";
  private Movie movie = new Movie(1, movieTitle);
  private Timestamp time = new Timestamp(121, 4, 15,
      13, 30, 0, 0);
  private String userName = "TestPerson";
  private String email = "test@test.dk";
  private String password = "1234";
  private User user = new User(1, userName, email, password);
  private Booking booking = new Booking(1, showing, user);

  public Hall getHall()
  {
    return hall;
  }

  public Showing getShowing()
  {
    return showing;
  }

  public String getMovieTitle()
  {
    return movieTitle;
  }

  public Movie getMovie()
  {
    return movie;
  }

  public Timestamp getTime()
  {
    return time;
  }

  public Booking getBooking()
  {
    return booking;
  }

  public String getUserName()
  {
    return userName;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }

  public User getUser()
  {
    return user;
  }

  public void setup() throws ServerException
  {
    resetDAO.reset();

    if (showing == null)
    {
      showing = new Showing(1, movie, time, hall);
    }
    hallDAO = new HallDAOImpl();

    if (hallDAO.getHallByNumber("A") == null)
    {
      movieDAO.create(movieTitle);
      hallDAO.create(hall);
      showingDAO.create(showing);
      userDAO.create(userName, email, password);
      bookingDAO.create(showing, user);
    }
  }
}
