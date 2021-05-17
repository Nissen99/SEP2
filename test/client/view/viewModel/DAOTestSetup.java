package client.view.viewModel;

import client.core.ClientFactory;
import client.network.RMIClient;
import server.dao.*;
import shared.exception.ServerException;
import shared.transferobjects.*;

import java.sql.Timestamp;
import java.util.ArrayList;

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
  private String password = "Test1234";
  private User user = new User(1, userName, email, password);
  private Booking booking = new Booking(1, showing, user);
  private RMIClient client = ClientFactory.getInstance().getClient();

  //Lists
  private ArrayList<Movie> movieList = new ArrayList<>();
  private ArrayList<Showing> showingList = new ArrayList<>();
  private ArrayList<Booking> bookingList = new ArrayList<>();
  private ArrayList<User> userList = new ArrayList<>();

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

  public ArrayList<Movie> getMovieList()
  {
    return movieList;
  }

  public ArrayList<Showing> getShowingList()
  {
    return showingList;
  }

  public ArrayList<Booking> getBookingList()
  {
    return bookingList;
  }

  public ArrayList<User> getUserList()
  {
    return userList;
  }

  public RMIClient getClient()
  {
    return client;
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
      hallDAO.create(hall);
      userList.add(userDAO.create(userName, email, password));
      movieList.add(movieDAO.create(movieTitle));
      showingList.add(showingDAO.create(showing));
      bookingList.add(bookingDAO.create(showing, user));

    }
  }
}
