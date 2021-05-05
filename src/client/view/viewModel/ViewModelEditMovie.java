package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModel;
import client.model.ClientModelMovie;
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

  private ClientModelMovie clientModel = ModelFactory.getInstance().getModelMovie();
  private ObservableList<Movie> movies = FXCollections.observableArrayList();

  public String getMovieTitle()
  {
    return movieTitle.get();
  }

  public StringProperty movieTitleProperty()
  {
    return movieTitle;
  }

  private StringProperty movieTitle = new SimpleStringProperty();


  private Movie selectedMovie;

  public ViewModelEditMovie()
  {

  }

  public void addMovie() throws SQLException, RemoteException
  {
    if (!getMovieTitle().equals("") && (getMovieTitle().charAt(0) != ' ') )
    {
      Movie movie = new Movie(getMovieTitle());
      clientModel.addMovie(movie);
    } else {
      throw new IllegalArgumentException("Invalid Title");
    }
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
