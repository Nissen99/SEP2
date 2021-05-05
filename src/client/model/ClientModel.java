package client.model;

import server.ServerException;
import server.model.PropertyChangeSubject;
import shared.transferobjects.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface ClientModel extends PropertyChangeSubject
{
  ArrayList<Movie> getMovieList() throws SQLException, RemoteException;
  // ArrayList<Booking> getBookingList();

}
