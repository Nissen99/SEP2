package Client.model;

import shared.transferobjects.Booking;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Model
{
  void addBooking(Showing showing, String username) throws SQLException;
  void addMovie(Movie movie);
  void addShowing(Showing showing);
  ArrayList<Movie> getMovieList();
  ArrayList<Showing> getShowingList();
  ArrayList<Booking> getBookingList();
  void setSelectedMovie(Movie movie);
  Movie getSelectedMovie();
  Showing getSelectedShowing();
  void setSelectedShowing(Showing showing);

}
