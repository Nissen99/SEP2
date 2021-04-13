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
import java.sql.SQLException;

public class MovieListController
{

  @FXML public TableView<Movie> tableViewForMovie;
  @FXML public TableColumn<Movie, String> movieTitleColumn;
  @FXML public Button confirmMovieChoice;

  private ViewModelMovieList viewModel = ViewModelFactory.getInstance().getMovieListViewModel();




  public void init() throws SQLException
  {

    tableViewForMovie.setItems(viewModel.getAllMovies());
    movieTitleColumn.setCellValueFactory(new PropertyValueFactory("movieTitle"));

  }

  public void confirmButtonPressed() throws IOException, SQLException
  {
    Movie movie = tableViewForMovie.getSelectionModel().getSelectedItem();

    viewModel.setSelectedMovie(movie);

    ViewHandler.getInstance().openView("../view/showingList/showingListView.fxml");

  }

}
