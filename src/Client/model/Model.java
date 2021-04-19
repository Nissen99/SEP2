package Client.model;

import shared.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Model
{
  Booking addBooking(Showing showing, String username, String seatNo) throws SQLException;
  Movie addMovie(Movie movie) throws SQLException;
  Showing addShowing(Showing showing) throws SQLException;
  Hall addHall(Hall hall) throws SQLException;
  ArrayList<Movie> getMovieList() throws SQLException;
  ArrayList<Showing> getShowingList(Movie movie) throws SQLException;
  // ArrayList<Booking> getBookingList();
  ArrayList<Seat> getOccupiedSeats(Showing showing) throws SQLException;

}
