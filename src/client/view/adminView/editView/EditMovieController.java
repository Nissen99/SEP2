package client.view.adminView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.Controller;
import client.view.viewModel.ViewModelEditMovie;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.exception.ServerException;
import shared.transferobjects.IMovie;

/**
 * Controller til editMovieView, står for at læse bruger inputs, dette er gjort
 * gennem et tableview og bindings med viewModellen.
 */

public class EditMovieController implements Controller
{
  @FXML public TextField movieTitleTextField;
  @FXML public TableView<IMovie> movieTableView;
  @FXML public TableColumn<IMovie, String> movieTitleColumn;
  private ViewModelEditMovie viewModel;

  public void init()
  {
    viewModel = ViewModelFactory.getInstance().getEditMovie();

    movieTitleTextField.textProperty()
        .bindBidirectional(viewModel.movieTitleProperty());

    setUpTableView();
  }

  private void setUpTableView()
  {
    try
    {
      movieTableView.setItems(viewModel.getAllMovies());
      movieTitleColumn
          .setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
    }
    catch (ServerException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!", e.getMessage());
      alert.show();
    }
  }

  public void editShowingButton()
  {
    try
    {
      IMovie movie = movieTableView.getSelectionModel().getSelectedItem();
      viewModel.setSelectedMovie(movie);

      ViewHandler.getInstance().openView("Edit Showing");

    }
    catch (NullPointerException e)
    {
      Alert alert = AlertBox
          .makeAlert("information", "Error!", "No movie selected");
      alert.show();
    }

  }

  public void back()
  {
    ViewHandler.getInstance().openView("Admin");
  }

  public void addMovie() throws ServerException
  {
    try
    {
      viewModel.addMovie();
      setUpTableView();
    }
    catch (IllegalArgumentException | NullPointerException e)
    {
      Alert alert = AlertBox
          .makeAlert("information", "Error!", "Invalid title");
      alert.show();
    }
    movieTitleTextField.clear();
  }

  public void removeMovie()
  {
    try
    {
      IMovie movie = movieTableView.getSelectionModel().getSelectedItem();
      viewModel.removeMovie(movie);
      setUpTableView();
    }

    catch (ServerException | NullPointerException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!", e.getMessage());
      alert.show();
    }

  }

}
