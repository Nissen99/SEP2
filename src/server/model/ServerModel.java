package server.model;

import server.ServerException;
import shared.transferobjects.*;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface ServerModel extends PropertyChangeSubject
{
  Booking addBooking(Showing showing,User user, ArrayList<Seat> seats)
      throws ServerException;
  void removeBooking(Booking booking) throws SQLException;
  Movie addMovie(Movie movie) throws SQLException;
  Showing addShowing(Showing showing) throws SQLException;
  Hall addHall(Hall hall) throws SQLException;
  ArrayList<Movie> getMovieList() throws SQLException;
  ArrayList<Showing> getShowingList(Movie movie) throws SQLException;
  ArrayList<Booking> getBookingList() throws SQLException;
  ArrayList<Seat> getOccupiedSeats(Showing showing) throws SQLException;
  void removeMovie(Movie movie) throws SQLException;

  Hall getHallByNumber(String hallNo) throws SQLException;
  ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo, Timestamp timestamp) throws SQLException;
  ArrayList<String> getHallNumbers() throws SQLException;
  void createUser(String userName, String email,String password) throws SQLException;
  User login(String userName, String password) throws LoginException;
}
