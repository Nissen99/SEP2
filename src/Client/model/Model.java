package Client.model;

import shared.transferobjects.Booking;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Model
{
  void addBooking(Showing showing, String username) throws SQLException;
  Movie addMovie(Movie movie) throws SQLException;
  void addShowing(Showing showing) throws SQLException;
  ArrayList<Movie> getMovieList();
  ArrayList<Showing> getShowingList();
  ArrayList<Booking> getBookingList();
  void setSelectedMovie(Movie movie);
  Movie getSelectedMovie();
  Showing getSelectedShowing();
  void setSelectedShowing(Showing showing);

}
