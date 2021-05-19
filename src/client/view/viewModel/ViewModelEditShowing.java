package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelShowing;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.exception.ServerException;
import shared.transferobjects.IMovie;
import shared.transferobjects.IShowing;

/**
 * ViewModel for editShowing, her håndteres den læste inputs fra controlleren.
 */
public class ViewModelEditShowing
{
  private IMovie selectedMovie;
  private StringProperty movieTitle = new SimpleStringProperty();
  private ClientModelShowing clientModel = ModelFactory.getInstance().getModelShowing();
  private ObservableList<IShowing> showings = FXCollections.observableArrayList();




  public ObservableList<IShowing> getAllShowings()
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
   * @param selectedMovie Hvilken film der blev valgt på forgående view
   */
  public void setSelectedMovie(IMovie selectedMovie)
  {
    this.selectedMovie = selectedMovie;
    movieTitle.setValue(selectedMovie.getMovieTitle());
  }

  public void removeShowing(IShowing showing) throws ServerException, NullPointerException
  {
    if (showing == null){
      throw new NullPointerException("Ingen filmfremvisning valgt");
    }
    clientModel.removeShowing(showing);
  }
}
