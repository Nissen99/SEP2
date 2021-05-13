package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelMovie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.exception.ServerException;
import shared.transferobjects.Movie;


public class ViewModelMovieList
{

  private ClientModelMovie clientModel = ModelFactory.getInstance().getModelMovie();
  private Movie selectedMovie;
  private ObservableList<Movie> movies = FXCollections.observableArrayList();


  public ObservableList<Movie> getAllMovies() throws ServerException

  {
   movies.removeAll(movies);
   movies.addAll(clientModel.getMovieList());

    return movies;
  }

  public void setSelectedMovie(Movie movie){
    if (movie == null){
      throw new NullPointerException("No movie selected");
    }
    this.selectedMovie = movie;
  }

  public Movie getSelectedMovie()
  {
    return selectedMovie;
  }


}
