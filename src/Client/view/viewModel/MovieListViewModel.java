package Client.view.viewModel;

import Client.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.Movie;

import java.util.ArrayList;

public class MovieListViewModel
{

  private Model modelManger;
  private ObservableList<Movie> movies = FXCollections.observableArrayList();


  public MovieListViewModel(Model model)
  {
    this.modelManger = model;
  }

  public ObservableList<Movie> getAllMovies(){

    movies.removeAll(movies);

   movies.addAll(modelManger.getMovieList());

    return movies;
  }

}
