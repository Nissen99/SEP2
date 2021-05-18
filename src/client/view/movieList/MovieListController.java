package client.view.movieList;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.Controller;
import client.view.viewModel.ViewModelMovieList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.exception.ServerException;
import shared.transferobjects.IMovie;

public class MovieListController implements Controller
{

  @FXML public TableView<IMovie> tableViewForMovie;
  @FXML public TableColumn<IMovie, String> movieTitleColumn;
  @FXML public Button confirmMovieChoice;
  private ViewModelMovieList viewModel = ViewModelFactory.getInstance().getMovieListViewModel();

/**
 * Controller til movieListView, står for at læse bruger inputs, dette er gjort
 * gennem et tableView og confirm/back knap.
 */

  public void init()
  {
    try
    {
      tableViewForMovie.setItems(viewModel.getAllMovies());
      movieTitleColumn.setCellValueFactory(new PropertyValueFactory("movieTitle"));
    }
    catch (ServerException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!", e.getMessage());
      alert.show();
    }

  }

  public void confirmButtonPressed()
  {
    try {
      IMovie movie = tableViewForMovie.getSelectionModel().getSelectedItem();
    viewModel.setSelectedMovie(movie);
    ViewHandler.getInstance().openView("Showing List");
  }
  catch (NullPointerException e){
    Alert alert = AlertBox.makeAlert("information","Select Movie", "No movie selected");
    alert.show();
  }
  }

  public void back()
  {
    ViewHandler.getInstance().openView("Login");
  }
}
