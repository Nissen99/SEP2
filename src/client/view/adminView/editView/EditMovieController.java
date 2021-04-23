package client.view.adminView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.viewModel.ViewModelEditMovie;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

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

    setUpTableView();
  }

  private void setUpTableView() throws SQLException, RemoteException
  {
    movieTableView.setItems(viewModel.getAllMovies());
    movieTitleColumn.setCellValueFactory(new PropertyValueFactory("movieTitle"));
  }

  public void editShowingButton() throws IOException, SQLException
  {
    Movie movie = movieTableView.getSelectionModel().getSelectedItem();

    viewModel.setSelectedMovie(movie);

    ViewHandler.getInstance().openView("../view/adminView/editView/editShowingView.fxml");
  }

  public void back() throws IOException, SQLException
  {
    ViewHandler.getInstance().openView("../view/adminView/adminView.fxml");
  }

  public void addMovie() throws SQLException, RemoteException
  {
    if (!movieTitleTextField.getText().equals(""))
    {
      Movie movie = new Movie(movieTitleTextField.getText());
      viewModel.addMovie(movie);
      movieTitleTextField.clear();
      setUpTableView();
    }
  }

  public void removeMovie(){
    //TODO
  }




}
