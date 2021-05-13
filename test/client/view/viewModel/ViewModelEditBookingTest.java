package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;

import java.rmi.RemoteException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelEditBookingTest
{
  private DAOTestSetup setup = new DAOTestSetup();
  private ViewModelEditBooking viewModel = new ViewModelEditBooking();

  @BeforeEach void setup() throws SQLException, ServerException
  {
    setup.setup();
  }

  @Test void testIfBookingsAreReturned()
      throws RemoteException, SQLException, ServerException
  {

    assertEquals(1, viewModel.getAllBookings().size());
  }

  @Test void testIfBookingIsRemoved()
      throws RemoteException, SQLException, ServerException
  {
    viewModel.removeBooking(setup.getBooking());
    assertEquals(0, viewModel.getAllBookings().size());
  }

}