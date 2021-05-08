package client.view.movieList;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.viewModel.ViewModelMovieList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Movie;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class MovieListController
{

  @FXML public TableView<Movie> tableViewForMovie;
  @FXML public TableColumn<Movie, String> movieTitleColumn;
  @FXML public Button confirmMovieChoice;

  private ViewModelMovieList viewModel = ViewModelFactory.getInstance().getMovieListViewModel();




  public void init() throws SQLException, RemoteException
  {

    tableViewForMovie.setItems(viewModel.getAllMovies());
    movieTitleColumn.setCellValueFactory(new PropertyValueFactory("movieTitle"));

  }

  public void confirmButtonPressed() throws IOException, SQLException
  {
    Movie movie = tableViewForMovie.getSelectionModel().getSelectedItem();

    if (movie == null){
      Alert alert = AlertBox
          .makeAlert("information","Select Movie", "No movie selected");
      alert.showAndWait();
    } else
    {
      viewModel.setSelectedMovie(movie);
      ViewHandler.getInstance().openView("../view/showingList/showingListView.fxml");
    }

  }


  public void back() throws IOException, SQLException
  {
    ViewHandler.getInstance().openView("../view/loginView/loginView.fxml");
  }
}
