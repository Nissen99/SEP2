package Client.view.viewModel;

import Client.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.Movie;

import java.sql.SQLException;

public class ViewModelMovieList
{

  private Model modelManger;
  private Movie selectedMovie;
  private ObservableList<Movie> movies = FXCollections.observableArrayList();


  public ViewModelMovieList(Model model)
  {
    this.modelManger = model;
  }

  public ObservableList<Movie> getAllMovies() throws SQLException
  {

    movies.removeAll(movies);

   movies.addAll(modelManger.getMovieList());

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
