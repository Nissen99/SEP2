package Client.view.movieList;

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
  @FXML public TableColumn<String, String> movieTitleColumn;
  @FXML public Button confirmMovieChoice;

  private MovieListViewModel viewModel = ViewModelFactory.getInstance().getMovieListViewModel();




  public void init(){
    tableViewForMovie = new TableView<>();
    movieTitleColumn = new TableColumn<>();
    confirmMovieChoice = new Button();

    movieTitleColumn.setCellValueFactory(new PropertyValueFactory("title"));

    tableViewForMovie.getItems().addAll(viewModel.getAllMovies());

  }

  public void confirmButtonPressed(){

    Movie movie = (Movie) tableViewForMovie.getSelectionModel().getSelectedItem();

  }

}
