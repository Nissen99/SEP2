package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelMovie;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.exception.ServerException;
import shared.transferobjects.Movie;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewModelEditMovie
{

  private ClientModelMovie clientModel = ModelFactory.getInstance().getModelMovie();
  private ObservableList<Movie> movies = FXCollections.observableArrayList();

  public String getMovieTitle()
  {
    return movieTitle.get();
  }

  public StringProperty movieTitleProperty()
  {
    return movieTitle;
  }

  private StringProperty movieTitle = new SimpleStringProperty();


  private Movie selectedMovie;

  /**
   * Vores eneste kriterieer for en valid film er at der skal være en title,
   * og at titlen ikke starter med ' ', for at prøve sikre os mod fejl input af brugeren
   *
   */
  public void addMovie() throws ServerException
  {
    if (!getMovieTitle().equals("") && (getMovieTitle().charAt(0) != ' ') )
    {
      Movie movie = new Movie(getMovieTitle());
      clientModel.addMovie(movie);
    } else {
      throw new IllegalArgumentException("Invalid Title");
    }
  }

  public ObservableList<Movie> getAllMovies() throws ServerException
  {

    movies.removeAll(movies);
    movies.addAll(clientModel.getMovieList());

    return movies;
  }

  public Movie getSelectedMovie()
  {
    return selectedMovie;
  }

  public void setSelectedMovie(Movie selectedMovie)
  {
    if (selectedMovie == null){
      throw new NullPointerException("No movie selected");
    }
    this.selectedMovie = selectedMovie;
  }

  public void removeMovie(Movie movie) throws ServerException
  {
      clientModel.removeMovie(movie);
  }


}
