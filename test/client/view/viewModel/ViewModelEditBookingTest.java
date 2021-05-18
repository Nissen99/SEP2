package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelEditBookingTest
{
  private VMTestSetup setup = new VMTestSetup();
  private ViewModelEditBooking viewModel = new ViewModelEditBooking();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test void testIfBookingsAreReturned()
      throws ServerException
  {

    assertEquals(setup.getBookingList().size(), viewModel.getAllBookings().size());
  }

  @Test void testIfBookingIsRemoved()
      throws ServerException
  {
    viewModel.removeBooking(setup.getBooking());
    assertEquals(setup.getBookingList().size()-1, viewModel.getAllBookings().size());
  }

}