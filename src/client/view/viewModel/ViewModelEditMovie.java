package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelMovie;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.exception.ServerException;
import shared.transferobjects.IMovie;
import shared.transferobjects.Movie;

public class ViewModelEditMovie
{

  private ClientModelMovie clientModel = ModelFactory.getInstance().getModelMovie();
  private ObservableList<IMovie> movies = FXCollections.observableArrayList();

  public String getMovieTitle()
  {
    return movieTitle.get();
  }

  public StringProperty movieTitleProperty()
  {
    return movieTitle;
  }

  private StringProperty movieTitle = new SimpleStringProperty();


  private IMovie selectedMovie;

  /**
   * Vores eneste kriterieer for en valid film er at der skal være en title,
   * og at titlen ikke starter med ' ', for at prøve sikre os mod fejl input af brugeren
   *
   */
  public void addMovie() throws ServerException
  {
    if (!getMovieTitle().equals("") && (getMovieTitle().charAt(0) != ' ') )
    {
      IMovie movie = new Movie(getMovieTitle());
      clientModel.addMovie(movie);
    } else {
      throw new IllegalArgumentException("Invalid Title");
    }
  }

  public ObservableList<IMovie> getAllMovies() throws ServerException
  {

    movies.removeAll(movies);
    movies.addAll(clientModel.getMovieList());

    return movies;
  }

  public IMovie getSelectedMovie()
  {
    return selectedMovie;
  }

  public void setSelectedMovie(IMovie selectedMovie) throws NullPointerException
  {
    if (selectedMovie == null){
      throw new NullPointerException("No movie selected");
    }
    this.selectedMovie = selectedMovie;

  }

  public void removeMovie(IMovie movie) throws ServerException, NullPointerException
  {
    if (movie == null){
      throw new NullPointerException("No movie selected");
    }
      clientModel.removeMovie(movie);
  }


}
