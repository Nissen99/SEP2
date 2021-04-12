package Client.view.movieList;

import Client.core.ViewHandler;
import Client.core.ViewModelFactory;
import Client.view.viewModel.MovieListViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Movie;

public class MovieListController
{

  @FXML public TableView<Movie> tableViewForMovie;
  @FXML public TableColumn<Movie, String> movieTitleColumn;
  @FXML public Button confirmMovieChoice;

  private MovieListViewModel viewModel = ViewModelFactory.getInstance().getMovieListViewModel();




  public void init(){


    tableViewForMovie.setItems(viewModel.getAllMovies());


    movieTitleColumn.setCellValueFactory(new PropertyValueFactory("movieTitle"));



  }

  public void confirmButtonPressed(){

    Movie movie = tableViewForMovie.getSelectionModel().getSelectedItem();
    System.out.println(movie);



  }

}
