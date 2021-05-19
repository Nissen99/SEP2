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
  private DAOTestSetup setup = new DAOTestSetup();
  private BookingDAO bookingDAO = new BookingDAOImpl();

  @BeforeEach
  public void setup() throws ServerException
  {
    setup.setup();
  }

  @Test
  public void testCreate() throws ServerException
  {
    assertEquals(2, bookingDAO.create(setup.getShowing(), setup.getUser()).getBookingId());
  }
}