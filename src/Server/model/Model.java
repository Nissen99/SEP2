package Server.model;

import shared.transferobjects.Booking;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.util.ArrayList;

public interface Model
{
  void addBooking(Booking booking);
  void addMovie(Movie movie);
  void addShowing(Showing showing);
  ArrayList<Movie> getMovieList();
  ArrayList<Showing> getShowingList();
  ArrayList<Booking> getBookingList();
}
