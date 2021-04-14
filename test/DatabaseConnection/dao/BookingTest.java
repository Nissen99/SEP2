package DatabaseConnection.dao;

import org.junit.jupiter.api.Test;
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
    bookingDAO.create(new Showing(1, new Movie(1, "Yikes"), new Timestamp(323232)), user);
  }
}