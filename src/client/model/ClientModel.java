package client.model;

import server.model.PropertyChangeSubject;
import shared.transferobjects.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface ClientModel extends PropertyChangeSubject
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
  Hall getHallByNumber(String hallNo) throws SQLException, RemoteException;
  ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo, Timestamp timestamp)
      throws RemoteException, SQLException;


}
