package DatabaseConnection.dao;

import org.junit.jupiter.api.Test;
import shared.Hall;
import shared.Movie;
import shared.Showing;
import shared.User;

import java.sql.SQLException;
import java.sql.Timestamp;

class BookingTest
{
  @Test
  public void testCreate() throws SQLException {
    BookingDAO bookingDAO = new BookingDAOImpl();
    User user = new User(1, "Hej");
    Hall hall = new Hall("S", 10, 10);
    Showing showing = new Showing(1, new Movie(1, "Yikes"), new Timestamp(323232), hall);
    bookingDAO.create(showing, user, "S"+109);
  }
}