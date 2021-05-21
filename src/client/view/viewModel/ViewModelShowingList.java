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
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * ViewModel for showingList, her håndteres den læste input fra controlleren.
 */
public class ViewModelShowingList
  {

    private IMovie movie;
    private ClientModelShowing clientModel = ModelFactory.getInstance()
        .getModelShowing();
    private ObservableList<IShowing> showings = FXCollections.observableArrayList();
    private IShowing selectedShowing;
    private StringProperty movieTitle = new SimpleStringProperty();

    public StringProperty movieTitleProperty()
    {
      return movieTitle;
    }

    /**
     * Vi vil kun vise showings i fremtiden, så der laves en liste af metoder
     * af alle metoder i fremtiden for en given film
     * @return list af gyldige showings
     * @throws ServerException connection til database eller server fejler
     */
    public ObservableList<IShowing> getFutureShowings()
        throws ServerException
    {
     Timestamp currentTime = new Timestamp(System.currentTimeMillis());

      ArrayList<IShowing> tempShowings = clientModel.getShowingList(movie);

      showings.clear();

      for (IShowing showing: tempShowings)
      {
        if (0 < showing.getTimestamp().compareTo(currentTime)) {
          showings.add(showing);
        }
      }

      return showings;
    }

    public String getMovieTitle(){
    return movie.getMovieTitle();
    }

    public void setSelectedShowing(IShowing showing) throws NullPointerException{
      if (showing == null){
        throw new NullPointerException("Ingen filmfremvisning valgt");
      }
    this.selectedShowing = showing;
    }

    public IShowing getSelectedShowing()
    {
      return selectedShowing;
    }

    public void setSelectedMovie(IMovie selectedMovie)
    {
      this.movie = selectedMovie;
      movieTitle.setValue(getMovieTitle());
    }


  }
