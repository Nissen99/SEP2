package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelEditBookingTest
{
  private DAOTestSetup setup = new DAOTestSetup();
  private ViewModelEditBooking viewModel = new ViewModelEditBooking();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test void testIfBookingsAreReturned()
      throws ServerException
  {

    assertEquals(1, viewModel.getAllBookings().size());
  }

  @Test void testIfBookingIsRemoved()
      throws ServerException
  {
    viewModel.removeBooking(setup.getBooking());
    assertEquals(0, viewModel.getAllBookings().size());
  }

}