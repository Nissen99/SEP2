package Client.model;

import jdbc.dao.*;
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
  private UserDAO userDAO;
  private MovieDAO movieDAO;
  private ShowingDAO showingDAO;

  public ModelManager()
  {
    this.showingList = new ShowingList();
    this.movieList = new MovieList();
    this.bookingList = new BookingList();

    this.bookingDAO = new BookingDAOImpl();
    this.userDAO = new UserDAOImpl();
    this.movieDAO = new MovieDAOImpl();
    this.showingDAO = new ShowingDAOImpl();
  }

  @Override public void addBooking(Showing showing, String username)
      throws SQLException
  {
    User user = userDAO.create(username);
    bookingDAO.create(showing, user);

  }

  @Override public Movie addMovie(Movie movie) throws SQLException
  {
  return movieDAO.create(movie.getMovieTitle());
  }

  @Override public void addShowing(Showing showing) throws SQLException
  {

    showingDAO.create(showing.getMovie(), showing.getTimestamp());
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
