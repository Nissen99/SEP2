package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewModelEditMovie
{

  private ClientModel clientModel = ModelFactory.getInstance().getModel();
  private ObservableList<Movie> movies = FXCollections.observableArrayList();


  private Movie selectedMovie;

  public ViewModelEditMovie()
  {

  }

  public void addMovie(Movie movie) throws SQLException, RemoteException
  {
    clientModel.addMovie(movie);
  }

  public ObservableList<Movie> getAllMovies()
      throws SQLException, RemoteException
  {

    movies.removeAll(movies);

    movies.addAll(clientModel.getMovieList());

    return movies;
  }

  public Movie getSelectedMovie()
  {
    return selectedMovie;
  }

  public void setSelectedMovie(Movie selectedMovie)
  {
    this.selectedMovie = selectedMovie;
  }



}
