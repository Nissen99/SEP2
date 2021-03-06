package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import shared.transferobjects.*;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

class ViewModelEditBookingTest
{
  private VMTestSetup setup = new VMTestSetup();
  private ViewModelEditBooking viewModel = new ViewModelEditBooking();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test void testListContainsCorrectBooking() throws ServerException
  {
    assertArrayEquals(setup.getBookingList().toArray(), viewModel.getAllBookings().toArray());
  }


  @Test void testIfBookingIsRemoved() throws ServerException
  {
    viewModel.removeBooking(setup.getBookingList().get(0));
    assertEquals(setup.getBookingList().size()-1, viewModel.getAllBookings().size());
  }

  @Test void testIfRightBookingIsRemoved() throws ServerException
  {
    IBooking booking = viewModel.getAllBookings().get(0);
    viewModel.removeBooking(booking);
    assertFalse(viewModel.getAllBookings().contains(booking));
  }

  @Test void testBookingByIdSearchForValidId() throws ServerException
  {
    viewModel.searchProperty().setValue("1");
    assertEquals(viewModel.getAllBookings().get(0), viewModel.getBookingById());
  }

  @Test void bookingByIdSearchForNoneValidId(){
    viewModel.searchProperty().setValue("37");
    assertThrows(NullPointerException.class, () -> viewModel.getBookingById());
  }

  @Test void testBookingByIdThrowsNumberFormat(){
    viewModel.searchProperty().setValue("A101");
    assertThrows(IllegalArgumentException.class, () -> viewModel.getBookingById());
  }


  @Test void whenBookingRemovedListChanges() throws ServerException
  {
    int listSize = viewModel.getAllBookings().size();
    viewModel.removeBooking(viewModel.getAllBookings().get(0));
    assertEquals(listSize - 1, viewModel.getAllBookings().size());
  }

  @Test void testRightElementIsRemoved() throws ServerException
  {
    IBooking booking = viewModel.getAllBookings().get(0);
    viewModel.removeBooking(booking);
    assertFalse(viewModel.getAllBookings().contains(booking));
  }

  @Test void ifBookingIsNullOnRemove()
  {
    assertThrows(NullPointerException.class, () -> viewModel.removeBooking(null));
  }

  @Test void removeSomethingNotOnList()
  {
    IShowing showing = new Showing(setup.getMovie(),new Timestamp(System.currentTimeMillis() + 3600000), setup.getHall());
    IUser user = new User(1337,"Henning", "Henning@gmail.com", "Henne1993");
    IBooking booking = new Booking(37, showing, user );
    assertThrows(ServerException.class, () ->viewModel.removeBooking(booking));
  }

}