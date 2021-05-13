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
import shared.exception.ServerException;
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
      alert.showAndWait();
    }

  }

  public void confirmButtonPressed()
  {

    Movie movie = tableViewForMovie.getSelectionModel().getSelectedItem();


    try
  {
    viewModel.setSelectedMovie(movie);
    ViewHandler.getInstance().openView("../view/showingList/showingListView.fxml");

  }
  catch (NullPointerException e){
    Alert alert = AlertBox.makeAlert("information","Select Movie", "No movie selected");
    alert.showAndWait();
  }

  }


  public void back()
  {
    ViewHandler.getInstance().openView("../view/loginView/loginView.fxml");
  }
}
