package client.view.adminView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.viewModel.ViewModelEditMovie;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.exception.ServerException;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import javax.swing.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditMovieController
{
  @FXML public TextField movieTitleTextField;
  @FXML public TableView<Movie> movieTableView;
  @FXML public TableColumn<Movie, String> movieTitleColumn;
  private ViewModelEditMovie viewModel;

  public void init() throws SQLException, RemoteException
  {
   viewModel = ViewModelFactory.getInstance().getEditMovie();

   movieTitleTextField.textProperty().bindBidirectional(viewModel.movieTitleProperty());

    setUpTableView();
  }


      private void setUpTableView() throws SQLException, RemoteException
      {
        movieTableView.setItems(viewModel.getAllMovies());
        movieTitleColumn.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));

      }

      public void editShowingButton()
          throws IOException, SQLException, ServerException
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

      public void back() throws IOException, SQLException, ServerException
      {
        ViewHandler.getInstance().openView("../view/adminView/adminView.fxml");
      }

  public void addMovie() throws SQLException, RemoteException, ServerException
  {
    try
    {
      viewModel.addMovie();
    }
    catch (IllegalArgumentException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!","Invalid title");
      alert.showAndWait();
    }
    movieTitleTextField.clear();
    setUpTableView();
  }

  public void removeMovie() throws SQLException, RemoteException
  {
    try
    {
      Movie movie = movieTableView.getSelectionModel().getSelectedItem();
      viewModel.removeMovie(movie);
    }
    catch ( NullPointerException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!","No movie selected");
      alert.showAndWait();
    }
    movieTitleTextField.clear();
    setUpTableView();

  }




}
