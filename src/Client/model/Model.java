package Client.model;

import shared.Booking;
import shared.Movie;
import shared.Showing;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Model
{
  Booking addBooking(Showing showing, String username) throws SQLException;
  Movie addMovie(Movie movie) throws SQLException;
  Showing addShowing(Showing showing) throws SQLException;
  ArrayList<Movie> getMovieList() throws SQLException;
  ArrayList<Showing> getShowingList() throws SQLException;
  // ArrayList<Booking> getBookingList();
  Movie getSelectedMovie();
  Showing getSelectedShowing();
  void setSelectedShowing(Showing showing);
  void setSelectedMovie(Movie movie);

}
