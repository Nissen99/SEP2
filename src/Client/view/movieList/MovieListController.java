package Client.view.movieList;

import Client.core.ViewHandler;
import Client.core.ViewModelFactory;
import Client.view.viewModel.ViewModelMovieList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Movie;

import java.io.IOException;

public class MovieListController
{

  @FXML public TableView<Movie> tableViewForMovie;
  @FXML public TableColumn<Movie, String> movieTitleColumn;
  @FXML public Button confirmMovieChoice;

  private ViewModelMovieList viewModel = ViewModelFactory.getInstance().getMovieListViewModel();




  public void init(){

    tableViewForMovie.setItems(viewModel.getAllMovies());
    movieTitleColumn.setCellValueFactory(new PropertyValueFactory("movieTitle"));

  }

  public void confirmButtonPressed() throws IOException
  {
    Movie movie = tableViewForMovie.getSelectionModel().getSelectedItem();

    viewModel.setSelectedMovie(movie);

    ViewHandler.getInstance().openView("../view/showingList/showingListView.fxml");

  }

}
