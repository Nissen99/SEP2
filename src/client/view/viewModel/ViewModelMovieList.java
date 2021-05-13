package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelMovie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.Movie;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewModelMovieList
{

  private ClientModelMovie clientModel = ModelFactory.getInstance().getModelMovie();
  private Movie selectedMovie;
  private ObservableList<Movie> movies = FXCollections.observableArrayList();


  public ObservableList<Movie> getAllMovies()
      throws SQLException, RemoteException
  {
   movies.removeAll(movies);
   movies.addAll(clientModel.getMovieList());

    return movies;
  }

  public void setSelectedMovie(Movie movie){
    this.selectedMovie = movie;
  }

  public Movie getSelectedMovie()
  {
    return selectedMovie;
  }


}
