package Client.model;

import shared.transferobjects.*;

import java.util.ArrayList;

public class ModelManager implements Model
{
  private ShowingList showingList;
  private MovieList movieList;
  private BookingList bookingList;

  public ModelManager(ShowingList showingList, MovieList movieList,
      BookingList bookingList)
  {
    this.showingList = showingList;
    this.movieList = movieList;
    this.bookingList = bookingList;
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
