package client.view.adminView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.viewModel.ViewModelEditMovie;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.exception.ServerException;
import shared.transferobjects.Movie;

public class EditMovieController
{
  @FXML public TextField movieTitleTextField;
  @FXML public TableView<Movie> movieTableView;
  @FXML public TableColumn<Movie, String> movieTitleColumn;
  private ViewModelEditMovie viewModel;

  public void init()
  {
   viewModel = ViewModelFactory.getInstance().getEditMovie();

   movieTitleTextField.textProperty().bindBidirectional(viewModel.movieTitleProperty());

   setUpTableView();
  }

      private void setUpTableView()
      {
        try
        {
          movieTableView.setItems(viewModel.getAllMovies());
          movieTitleColumn.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        }
        catch (ServerException e)
        {
          Alert alert = AlertBox.makeAlert("information", "Error!", e.getMessage());
          alert.showAndWait();
        }
      }

      public void editShowingButton()
      {
        try
        {
          Movie movie = movieTableView.getSelectionModel().getSelectedItem();
          viewModel.setSelectedMovie(movie);

      ViewHandler.getInstance().openView("../view/adminView/editView/editShowingView.fxml");
    } catch (NullPointerException e) {
      Alert alert = AlertBox.makeAlert("information", "Error!","No movie selected");
      alert.showAndWait();
    }

  }

      public void back()
      {
        ViewHandler.getInstance().openView("../view/adminView/adminView.fxml");
      }

  public void addMovie() throws  ServerException
  {
    try
    {
      viewModel.addMovie();
      setUpTableView();
    }
    catch (IllegalArgumentException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!","Invalid title");
      alert.showAndWait();
    }
    movieTitleTextField.clear();
  }

  public void removeMovie()
  {
    try
    {
      Movie movie = movieTableView.getSelectionModel().getSelectedItem();
      viewModel.removeMovie(movie);
      setUpTableView();
    }

    catch (ServerException | NullPointerException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!",e.getMessage());
      alert.showAndWait();
    }

  }

}
