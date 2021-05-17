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

import java.sql.Timestamp;
import java.util.ArrayList;

public class ViewModelShowingList
  {

    private Movie movie;
    private ClientModelShowing clientModel = ModelFactory.getInstance()
        .getModelShowing();
    private ObservableList<Showing> showings = FXCollections.observableArrayList();
    private Showing selectedShowing;
    private StringProperty movieTitle = new SimpleStringProperty();

    public StringProperty movieTitleProperty()
    {
      System.out.println(movieTitle.toString());
      return movieTitle;
    }
    public String getmovieTitle()
    {
      return movieTitle.get();
    }

    public ObservableList<Showing> getAllShowings()
        throws ServerException
    {
      ArrayList<Showing> tempShowings = new ArrayList<>();

      showings.removeAll(showings);

      tempShowings.addAll(clientModel.getShowingList(movie));

      Timestamp currentTime = new Timestamp(System.currentTimeMillis());

      for (Showing showing: tempShowings)
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

    public void setSelectedShowing(Showing showing) throws NullPointerException{
      if (showing == null){
        throw new NullPointerException("No showing selected");
      }
    this.selectedShowing = showing;
    }

    public Showing getSelectedShowing()
    {
      return selectedShowing;
    }

    public void setSelectedMovie(Movie selectedMovie)
    {
      this.movie = selectedMovie;
      movieTitle.setValue(getMovieTitle());
    }


  }
