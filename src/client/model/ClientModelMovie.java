package client.model;

import shared.transferobjects.Movie;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ClientModelMovie extends ClientModel
{
  Movie addMovie(Movie movie) throws SQLException, RemoteException;
  void removeMovie(Movie movie) throws RemoteException, SQLException;
}
