package server.model;

import shared.transferobjects.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServerModel
{
  Booking addBooking(Showing showing, String username, ArrayList<Seat> seats) throws SQLException;
  Movie addMovie(Movie movie) throws SQLException;
  Showing addShowing(Showing showing) throws SQLException;
  Hall addHall(Hall hall) throws SQLException;
  ArrayList<Movie> getMovieList() throws SQLException;
  ArrayList<Showing> getShowingList(Movie movie) throws SQLException;
  // ArrayList<Booking> getBookingList();
  ArrayList<Seat> getOccupiedSeats(Showing showing) throws SQLException;

  Hall getHallByNumber(String hallNo) throws SQLException;
}
