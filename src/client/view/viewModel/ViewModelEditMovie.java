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

/**
 * ViewModel for editMovie, her håndteres den læste data fra controlleren,
 * dette sker gennem bindings på forskellige Properties.
 */
public class ViewModelEditMovie
{

  private ClientModelMovie clientModel = ModelFactory.getInstance().getModelMovie();
  private ObservableList<IMovie> movies = FXCollections.observableArrayList();
  private StringProperty movieTitle = new SimpleStringProperty();
  private IMovie selectedMovie;

  /**
   * Vores eneste kriterieer for en valid film er at der skal være en title,
   * og at titlen ikke starter med ' ', for at prøve sikre os mod fejl input af brugeren
   * @throws IllegalArgumentException Hvis title er ugyldig
   */
  public void addMovie() throws ServerException, IllegalArgumentException
  {
    if (!getMovieTitle().equals("") && (getMovieTitle().charAt(0) != ' ') )
    {
      IMovie movie = new Movie(getMovieTitle());
      clientModel.addMovie(movie);
    } else {
      throw new IllegalArgumentException("Ugyldig Title");
    }
  }

  public ObservableList<IMovie> getAllMovies() throws ServerException
  {
    movies.clear();
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
      throw new NullPointerException("Ingen film valgt");
    }
    this.selectedMovie = selectedMovie;

  }

  public void removeMovie(IMovie movie) throws ServerException, NullPointerException
  {
    if (movie == null){
      throw new NullPointerException("Ingen film valgt");
    }
      clientModel.removeMovie(movie);
  }

  public String getMovieTitle()
  {
    return movieTitle.get();
  }

  public StringProperty movieTitleProperty()
  {
    return movieTitle;
  }


}
