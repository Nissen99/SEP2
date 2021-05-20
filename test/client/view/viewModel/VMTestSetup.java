package client.view.viewModel;

import client.core.ClientFactory;
import client.network.RMIClient;
import server.dao.*;
import shared.exception.ServerException;
import shared.transferobjects.*;

import java.sql.Timestamp;
import java.util.ArrayList;

public class VMTestSetup
{
  private HallDAOImpl hallDAO;
  private MovieDAO movieDAO = new MovieDAOImpl();
  private ShowingDAO showingDAO = new ShowingDAOImpl();
  private UserDAO userDAO = new UserDAOImpl();
  private BookingDAO bookingDAO = new BookingDAOImpl();
  private ResetDAO resetDAO = new ResetDAO();
  private IHall hall = new Hall("A", 5, 5);
  private IShowing showing = null;
  private String movieTitle = "Jackass";
  private IMovie movie = new Movie(1, movieTitle);
  private Timestamp time = new Timestamp(121, 6, 15,
      13, 30, 0, 0);
  private String userName = "TestPerson";
  private String email = "test@test.dk";
  private String password = "Test1234";
  private IUser user = new User(1, userName, email, password);
  private IBooking booking;
  private RMIClient client = ClientFactory.getInstance().getClient();

  //Lists
  private ArrayList<IMovie> movieList = new ArrayList<>();
  private ArrayList<IShowing> showingList = new ArrayList<>();
  private ArrayList<IBooking> bookingList = new ArrayList<>();
  private ArrayList<IUser> userList = new ArrayList<>();

  public IHall getHall()
  {
    return hall;
  }

  public IShowing getShowing()
  {
    return showing;
  }

  public String getMovieTitle()
  {
    return movieTitle;
  }

  public IMovie getMovie()
  {
    return movie;
  }

  public Timestamp getTime()
  {
    return time;
  }

  public IBooking getBooking()
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

  public IUser getUser()
  {
    return user;
  }

  public ArrayList<IMovie> getMovieList()
  {
    return movieList;
  }

  public ArrayList<IShowing> getShowingList()
  {
    return showingList;
  }

  public ArrayList<IBooking> getBookingList()
  {
    return bookingList;
  }

  public ArrayList<IUser> getUserList()
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
      booking = new Booking(1, showing, user);
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
