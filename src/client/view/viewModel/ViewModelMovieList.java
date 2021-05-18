package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelMovie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.exception.ServerException;
import shared.transferobjects.IMovie;

public class ViewModelMovieList
{

  private ClientModelMovie clientModel = ModelFactory.getInstance().getModelMovie();
  private IMovie selectedMovie;
  private ObservableList<IMovie> movies = FXCollections.observableArrayList();


  public ObservableList<IMovie> getAllMovies() throws ServerException
  {
   movies.removeAll(movies);
   movies.addAll(clientModel.getMovieList());

    return movies;
  }

  public void setSelectedMovie(IMovie movie) throws NullPointerException
  {
    if (movie == null){
      throw new NullPointerException("No movie selected");
    }
    this.selectedMovie = movie;
  }

  public IMovie getSelectedMovie()
  {
    return selectedMovie;
  }


}
