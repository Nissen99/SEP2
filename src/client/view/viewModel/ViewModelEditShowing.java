package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewModelEditShowing
{
  private Movie selectedMovie;
  private StringProperty movieTitle = new SimpleStringProperty();
  private ClientModel clientModel = ModelFactory.getInstance().getModel();
  private ObservableList<Showing> showings = FXCollections.observableArrayList();



  public String getMovieTitle(){
    return selectedMovie.getMovieTitle();
  }

  public ObservableList<Showing> getAllShowings()
      throws SQLException, RemoteException
  {
    showings.removeAll(showings);

    showings.addAll(clientModel.getShowingList(selectedMovie));

    return showings;
  }

  public StringProperty movieTitleProperty()
  {
    return movieTitle;
  }

  public void setSelectedMovie(Movie selectedMovie)
  {
    this.selectedMovie = selectedMovie;
    movieTitle.setValue(selectedMovie.getMovieTitle());
  }
}
