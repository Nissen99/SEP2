package Client.model;

import DatabaseConnection.dao.*;
import shared.transferobjects.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManager implements Model
{
  BookingDAO bookingDAO;

  private Movie selectedMovie = null;
  private Showing selectedShowing = null;
  private UserDAO userDAO;
  private MovieDAO movieDAO;
  private ShowingDAO showingDAO;

  public ModelManager()
  {
     this.bookingDAO = new BookingDAOImpl();
    this.userDAO = new UserDAOImpl();
    this.movieDAO = new MovieDAOImpl();
    this.showingDAO = new ShowingDAOImpl();
  }

  @Override public Booking addBooking(Showing showing, String username)
      throws SQLException
  {
    User user = userDAO.create(username);
   return bookingDAO.create(showing, user);

  }

  @Override public Movie addMovie(Movie movie) throws SQLException
  {
  return movieDAO.create(movie.getMovieTitle());
  }

  @Override public Showing addShowing(Showing showing) throws SQLException
  {

    return showingDAO.create(showing.getMovie(), showing.getTimestamp());

  }

  @Override public ArrayList<Movie> getMovieList() throws SQLException
  {
      return movieDAO.getAllMovies();

  }

  @Override public ArrayList<Showing> getShowingList() throws SQLException
  {

    return showingDAO.getAllShowings(selectedMovie);
  }

//  @Override public ArrayList<Booking> getBookingList()
//  {
//    return bookingDAO.getAllBookings();
//  }


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
