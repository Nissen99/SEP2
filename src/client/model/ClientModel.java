package client.model;

import client.network.Client;
import shared.exception.ServerException;
import shared.transferobjects.*;

import java.util.ArrayList;

public interface ClientModel
{
  ArrayList<IMovie> getMovieList() throws ServerException;
  Client getClient();

}
