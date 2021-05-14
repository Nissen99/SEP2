package client.model;

import shared.util.PropertyChangeSubject;
import shared.exception.ServerException;
import shared.transferobjects.Booking;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;

import java.util.ArrayList;

public interface ClientModelBooking extends ClientModelShowingList,
    PropertyChangeSubject
{
  void addBooking(Showing showing,  ArrayList<Seat> seats)
      throws ServerException;
  void removeBooking(Booking booking) throws ServerException;
  ArrayList<Seat> getOccupiedSeats(Showing showing) throws ServerException;
  ArrayList<Booking> getBookingList() throws ServerException;
}
