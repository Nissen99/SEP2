package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelShowing;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.exception.ServerException;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;


public class ViewModelEditShowing
{
  private Movie selectedMovie;
  private StringProperty movieTitle = new SimpleStringProperty();
  private ClientModelShowing clientModel = ModelFactory.getInstance().getModelShowing();
  private ObservableList<Showing> showings = FXCollections.observableArrayList();



  public String getMovieTitle(){
    return selectedMovie.getMovieTitle();
  }

  public ObservableList<Showing> getAllShowings()
      throws ServerException
  {
    showings.clear();

    showings.addAll(clientModel.getShowingList(selectedMovie));

    return showings;
  }

  public StringProperty movieTitleProperty()
  {
    return movieTitle;
  }


  /**
   * For at vide hvilken film der skal laves showings til skal vi vide hvad
   * brugeren har valgt på det forgående view, den information sender vi gennem
   * denne metode der bliver kaldt i ViewModelFactory.
   * @param selectedMovie
   */
  public void setSelectedMovie(Movie selectedMovie)
  {
    this.selectedMovie = selectedMovie;
    movieTitle.setValue(selectedMovie.getMovieTitle());
  }

  public void removeShowing(Showing showing) throws ServerException, NullPointerException
  {
    if (showing == null){
      throw new NullPointerException("No showing selected");
    }
    clientModel.removeShowing(showing);
  }
}
