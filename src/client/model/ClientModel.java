package client.model;

import shared.transferobjects.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ClientModel
{
  Booking addBooking(Showing showing, String username, ArrayList<Seat> seats)
      throws RemoteException, SQLException;
  Movie addMovie(Movie movie) throws SQLException, RemoteException;
  Showing addShowing(Showing showing) throws SQLException, RemoteException;
  Hall addHall(Hall hall) throws SQLException, RemoteException;
  ArrayList<Movie> getMovieList() throws SQLException, RemoteException;
  ArrayList<Showing> getShowingList(Movie movie)
      throws SQLException, RemoteException;
  // ArrayList<Booking> getBookingList();
  ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws SQLException, RemoteException;

}
