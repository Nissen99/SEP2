package client.view.movieList;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.Controller;
import client.view.loginView.LoginViewController;
import client.view.showingList.ShowingListController;
import client.view.viewModel.ViewModelMovieList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.exception.ServerException;
import shared.transferobjects.IMovie;

/**
 * Controller til movieListView, står for at læse bruger inputs, dette er gjort
 * gennem et tableView og confirm/back knap.
 */

public class MovieListController implements Controller
{
  private String path = "movieList/movieList";
  @FXML public TableView<IMovie> tableViewForMovie;
  @FXML public TableColumn<IMovie, String> movieTitleColumn;
  @FXML public Button confirmMovieChoice;
  private ViewModelMovieList viewModel = ViewModelFactory.getInstance()
      .getMovieListViewModel();

  public void init()
  {
    try
    {
      tableViewForMovie.setItems(viewModel.getAllMovies());
      movieTitleColumn
          .setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
    }
    catch (ServerException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!", e.getMessage());
      alert.show();
    }

  }

  @Override public String getPath()
  {
    return path;
  }

  public void confirmButtonPressed()
  {
    try
    {
      IMovie movie = tableViewForMovie.getSelectionModel().getSelectedItem();
      viewModel.setSelectedMovie(movie);
      ViewHandler.getInstance().setState(new ShowingListController());
      ViewHandler.getInstance().openView();
      //ViewHandler.getInstance().openView("Showing List");
    }
    catch (NullPointerException e)
    {
      Alert alert = AlertBox
          .makeAlert("information", "Select Movie", "No movie selected");
      alert.show();
    }
  }

  public void back()
  {

    Alert alert = AlertBox
        .makeAlert("confirmation", "Log ud", "Vil du gerne logge ud?");
    alert.showAndWait().ifPresent(type -> {
      if (type.getButtonData() == ButtonBar.ButtonData.YES)
      {
        ViewHandler.getInstance().setState(new LoginViewController());
        ViewHandler.getInstance().openView();
        //ViewHandler.getInstance().openView("Login");
      }
    });
  }
}

