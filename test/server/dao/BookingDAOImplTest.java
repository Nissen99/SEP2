package server.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import shared.transferobjects.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


class BookingDAOImplTest
{
  private SeatDAO seatDAO = new SeatDAOImpl();
  private BookingDAO bookingDAO;
  private User user;
  private UserDAO userDAO;
  private Hall hall;
  private HallDAO hallDAO;
  private Movie movie;
  private MovieDAO movieDAO;
  private Showing showing;
  private ShowingDAO showingDAO;


  @BeforeEach
  public void setUp() throws SQLException, ServerException
  {
    bookingDAO= new BookingDAOImpl();
    user = new User(1, "Hej","hejdu@gmail.com","pass123");
    userDAO = new UserDAOImpl();
    userDAO.create("hans","hans@hotmail.com","pass123");
  }



  @Test
  public void testCreate() throws SQLException, ServerException
  {
    setUpGood("T");

    showingDAO.create(showing);
    bookingDAO.create(showing, user);
  }

  @Test
  public void testGetOccupiedSeats() throws SQLException, ServerException
  {
    setUpGood("Y");

    ArrayList<Seat> seatArrayList = new ArrayList<>();
    Seat seat = new Seat();
    seat.setSeatNo("Y101");
    Seat seat2 = new Seat();
    seat2.setSeatNo("Y102");
    Seat seat3 = new Seat();
    seat3.setSeatNo("Y103");


    seatArrayList.add(seat);
    seatArrayList.add(seat2);
    seatArrayList.add(seat3);


    bookingDAO.create(showing,user);
    bookingDAO.create(showing,user);
    bookingDAO.create(showing,user);

    assertEquals(seatArrayList.get(0).getSeatNo(), bookingDAO.getOccupiedSeats(showing).get(0).getSeatNo());
    assertEquals(seatArrayList.get(1).getSeatNo(), bookingDAO.getOccupiedSeats(showing).get(1).getSeatNo());
    assertEquals(seatArrayList.get(2).getSeatNo(), bookingDAO.getOccupiedSeats(showing).get(2).getSeatNo());


  }


  //Help metode til setup da vi skal bruge forskellige hallNo's
  private void setUpGood(String hallNo) throws SQLException, ServerException
  {

    hall = new Hall(hallNo, 5, 5);
    hallDAO = new HallDAOImpl();
    hallDAO.create(hall);
    for (int i = 0; i < hall.getMaxRows() * hall.getMaxSeatsInRow(); i++)
    {
      Seat seat = new Seat();
      seatDAO.create(hall.addSeat(seat), hall);
    }
    movie = new Movie(1, "Yikes");
    movieDAO = new MovieDAOImpl();
    movieDAO.create(movie.getMovieTitle());
    showing = new Showing(1, movie, new Timestamp(323232), hall);
    showingDAO = new ShowingDAOImpl();
    showingDAO.create(showing);
  }
}