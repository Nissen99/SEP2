package DatabaseConnection.dao;

import org.junit.jupiter.api.Test;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;
import shared.transferobjects.User;

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