package databaseConnection.dao;

import org.junit.jupiter.api.Test;
import shared.*;

import java.sql.SQLException;
import java.sql.Timestamp;

class BookingDAOImplTest
{
  private SeatDAO seatDAO = new SeatDAOImpl();

  @Test
  public void testCreate() throws SQLException {
    BookingDAO bookingDAO = new BookingDAOImpl();
    User user = new User(1, "Hej");
    UserDAO userDAO = new UserDAOImpl();
    userDAO.create(user.getName());
    Hall hall = new Hall("T", 5, 5);
    HallDAO hallDAO = new HallDAOImpl();
    hallDAO.create(hall);
    for (int i = 0; i < hall.getMaxRows() * hall.getMaxSeatsInRow(); i++)
    {
      Seat seat = new Seat();
      seatDAO.create(hall.addSeat(seat), hall);
    }
    Movie movie = new Movie(1, "Yikes");
    MovieDAO movieDAO = new MovieDAOImpl();
    movieDAO.create(movie.getMovieTitle());
    Showing showing = new Showing(1, movie, new Timestamp(323232), hall);
    ShowingDAO showingDAO = new ShowingDAOImpl();
    showingDAO.create(showing);
    bookingDAO.create(showing, user, hall.getHallNo() + "101");
  }
}