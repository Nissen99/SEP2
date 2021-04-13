package Client.model;

import jdbc.dao.BookingDAO;
import jdbc.dao.BookingDAOImpl;
import shared.transferobjects.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class ModelManager implements Model
{
  private ShowingList showingList;
  BookingDAO bookingDAO;
  private MovieList movieList;
  private BookingList bookingList;
  private Movie selectedMovie = null;
  private Showing selectedShowing = null;

  public ModelManager()
  {
    this.showingList = new ShowingList();
    this.movieList = new MovieList();
    this.bookingList = new BookingList();
    bookingDAO = new BookingDAOImpl();
  }

  @Override public void addBooking(Showing showing, String username)
      throws SQLException
  {
    bookingDAO.create(showing, username);

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


  //Nok ikke godt, men det kør vi med nu
  public void setSelectedMovie(Movie movie){
    this.selectedMovie = movie;
  }

  public Movie getSelectedMovie(){
    return selectedMovie;
  }

  public void setSelectedShowing(Showing showing){
    this.selectedShowing = showing;
  }

  public Showing getSelectedShowing(){
    return selectedShowing;
  }
}
