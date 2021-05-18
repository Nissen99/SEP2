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

    public ObservableList<IShowing> getAllShowings()
        throws ServerException
    {
      ArrayList<IShowing> tempShowings = new ArrayList<>();

      showings.removeAll(showings);

      tempShowings.addAll(clientModel.getShowingList(movie));

      Timestamp currentTime = new Timestamp(System.currentTimeMillis());

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
        throw new NullPointerException("No showing selected");
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
