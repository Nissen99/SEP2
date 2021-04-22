package client.view.viewModel;

import client.model.ClientModel;
import shared.transferobjects.Movie;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewModelEditMovie
{

  private ClientModel clientModel;

  public ViewModelEditMovie(ClientModel model)
  {
    this.clientModel = model;
  }

  public void addMovie(Movie movie) throws SQLException, RemoteException
  {
    clientModel.addMovie(movie);
  }

}
