package Server.model;

import shared.Booking;
import shared.Movie;
import shared.Showing;

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
