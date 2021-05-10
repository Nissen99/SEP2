package client.model;

import shared.exception.ServerException;
import shared.transferobjects.Booking;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ClientModelBooking extends ClientModelShowingList
{
  Booking addBooking(Showing showing,  ArrayList<Seat> seats)
      throws RemoteException, SQLException, ServerException;
  void removeBooking(Booking booking) throws RemoteException, SQLException;
  ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws SQLException, RemoteException;
  ArrayList<Booking> getBookingList() throws RemoteException, SQLException;
}
