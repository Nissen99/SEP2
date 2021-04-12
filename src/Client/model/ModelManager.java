package Client.model;

import shared.transferobjects.*;

import java.util.ArrayList;

public class ModelManager implements Model
{
  private ShowingList showingList;
  private MovieList movieList;
  private BookingList bookingList;

  public ModelManager()
  {
    this.showingList = new ShowingList();
    this.movieList = new MovieList();
    this.bookingList = new BookingList();
  }

  @Override public void addBooking(Booking booking)
  {
    bookingList.addBooking(booking);
  }

  @Override public void addMovie(Movie movie)
  {
    movieList.addMovie(movie);
  }

  @Override public void addShowing(Showing showing)
  {
    showingList.addShowing(showing);
  }

  @Override public ArrayList<Movie> getMovieList()
  {
    movieList.addMovie(new Movie("Yikes"));
    return movieList.getMovieList();
  }

  @Override public ArrayList<Showing> getShowingList()
  {
    return showingList.getShowingList();
  }

  @Override public ArrayList<Booking> getBookingList()
  {
    return bookingList.getBookingList();
  }
}
