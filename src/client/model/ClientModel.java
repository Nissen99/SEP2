package client.model;

import client.network.Client;
import shared.exception.ServerException;
import shared.transferobjects.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ClientModel
{
  ArrayList<Movie> getMovieList() throws ServerException;
  Client getClient();

}
