package server.dao;

import server.util.SeatNoCalculator;
import shared.exception.ServerException;
import shared.transferobjects.*;

import java.sql.Timestamp;
import java.util.ArrayList;

public class DAOTestSetup
{
  private ResetDAOImpl resetDAO = new ResetDAOImpl();
  private SeatDAO seatDAO = new SeatDAOImpl();
  private BookingDAO bookingDAO = new BookingDAOImpl();
  private UserDAO userDAO = new UserDAOImpl();
  private HallDAO hallDAO = new HallDAOImpl();
  private MovieDAO movieDAO = new MovieDAOImpl();
  private ShowingDAO showingDAO = new ShowingDAOImpl();

  private String userName = "TestPerson";
  private String password = "Test1234";
  private String email = "test@test.dk";

  private User user = new User(1, userName, email, password);
  private Hall hall = new Hall("A", 3, 3);
  private Movie movie = new Movie(1, "Saving Private Ryan");
  private Showing showing = new Showing(1, movie, new Timestamp(323232), hall);
  private Booking booking = new Booking(1, showing, user);

  //Lists
  private ArrayList<IMovie> movieList = new ArrayList<>();

  public ArrayList<IBooking> getBookingList()
  {
    return bookingList;
  }

  public ArrayList<IUser> getUserList()
  {
    return userList;
  }

  private ArrayList<IBooking> bookingList = new ArrayList<>();
  private ArrayList<IUser> userList = new ArrayList<>();


  private SeatNoCalculator seatNoCalculator = new SeatNoCalculator("A", 3, 3);

  public void setup() throws ServerException
  {
    resetDAO.reset();
    hallDAO.create(hall);

    for (int i = 0; i < hall.getMaxRows() * hall.getMaxSeatsInRow(); i++)
    {
      Seat seat = new Seat();
      seat.setSeatNo(seatNoCalculator.calculateSeatNo());
      seatDAO.create(hall.addSeat(seat), hall);
    }

    movieList.add(movieDAO.create(movie.getMovieTitle()));
    showingDAO.create(showing);
    userList.add(userDAO.create(userName,email,password));
    bookingList.add(bookingDAO.create(showing, user));
  }

  public String getUserName()
  {
    return userName;
  }

  public String getPassword()
  {
    return password;
  }

  public String getEmail()
  {
    return email;
  }

  public User getUser()
  {
    return user;
  }

  public Hall getHall()
  {
    return hall;
  }

  public Movie getMovie()
  {
    return movie;
  }

  public Showing getShowing()
  {
    return showing;
  }

  public Booking getBooking()
  {
    return booking;
  }
}
