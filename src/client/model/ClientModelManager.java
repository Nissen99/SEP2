package client.model;

import client.network.Client;
import client.network.RMIClient;
import databaseConnection.dao.*;
import shared.transferobjects.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientModelManager implements ClientModel
{
  private RMIClient client;
  private BookingDAO bookingDAO;
  private UserDAO userDAO;
  private MovieDAO movieDAO;
  private ShowingDAO showingDAO;
  private HallDAO hallDAO;

  public ClientModelManager(RMIClient client)
  {
    this.client = client;
    this.bookingDAO = new BookingDAOImpl();
    this.userDAO = new UserDAOImpl();
    this.movieDAO = new MovieDAOImpl();
    this.showingDAO = new ShowingDAOImpl();
    this.hallDAO = new HallDAOImpl();
  }

  @Override public Booking addBooking(Showing showing, String username,
      String seatNo) throws RemoteException, SQLException
  {
    return client.addBooking(showing, username, seatNo);
  }

  @Override public Movie addMovie(Movie movie)
      throws SQLException, RemoteException
  {
    return client.addMovie(movie);
  }

  @Override public Showing addShowing(Showing showing)
      throws SQLException, RemoteException
  {

    return client.addShowing(showing);

  }

  @Override public Hall addHall(Hall hall) throws SQLException, RemoteException
  {
    return client.addHall(hall);
  }

  @Override public ArrayList<Movie> getMovieList()
      throws RemoteException, SQLException
  {
      return client.getMovieList();

  }

  @Override public ArrayList<Showing> getShowingList(Movie movie)
      throws RemoteException, SQLException
  {

    return client.getShowingList(movie);
  }

  @Override public ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws RemoteException, SQLException
  {
    return client.getOccupiedSeats(showing);
  }

  @Override public Hall getHallByNumber(String hallNo)
      throws SQLException, RemoteException
  {
    return client.getHallByNumber(hallNo);
  }

}
