package shared.transferobjects;

import java.util.ArrayList;

public class BookingList
{
  private ArrayList<Booking> bookingList = new ArrayList<>();

  public void addBooking(Booking booking)
  {
    bookingList.add(booking);
  }

  public ArrayList<Booking> getBookingList()
  {
    return bookingList;
  }
}
