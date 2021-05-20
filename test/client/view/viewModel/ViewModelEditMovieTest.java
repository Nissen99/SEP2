package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import shared.transferobjects.IMovie;
import shared.transferobjects.Movie;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Vi kan ikke teste addMovie, da den er bindet med GUI, derfor laver vi test af
 * addMovie på modellen
 */
class ViewModelEditMovieTest
{
  private VMTestSetup setup = new VMTestSetup();
  private ViewModelEditMovie viewModel = new ViewModelEditMovie();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }


  @Test void setSelectedMovie(){
  viewModel.setSelectedMovie(setup.getMovie());
  assertEquals(setup.getMovie().getMovieId(), viewModel.getSelectedMovie().getMovieId());
  }

  @Test void setSelectedMovieAsNull(){
    assertThrows(NullPointerException.class, () -> viewModel.setSelectedMovie(null));
  }

  @Test void whenMovieRemovedListChanges() throws ServerException
  {
    int listSize = viewModel.getAllMovies().size();
    viewModel.removeMovie(viewModel.getAllMovies().get(0));
    assertEquals(listSize-1, viewModel.getAllMovies().size());
  }

  @Test void rightElementGetsRemoved() throws ServerException
  {
    IMovie movie = viewModel.getAllMovies().get(0);
    viewModel.removeMovie(movie);

    assertFalse(viewModel.getAllMovies().contains(movie));
  }

  @Test void testAddMovie() throws ServerException
  {
    viewModel.movieTitleProperty().setValue("Spiderman 3 the dark knight returns");
    viewModel.addMovie();

    assertEquals("Spiderman 3 the dark knight returns", viewModel.getAllMovies().get(1).getMovieTitle());
  }

  @Test void testAddMovieMovieTitleAsNull(){
    assertThrows(NullPointerException.class, () -> viewModel.addMovie());
  }

  @Test void testAddMovieMovieTitleAsEmpty(){
    viewModel.movieTitleProperty().setValue("");
    assertThrows(ServerException.class, () -> viewModel.addMovie());
    viewModel.movieTitleProperty().setValue(" ");
    assertThrows(ServerException.class, () -> viewModel.addMovie());
  }

  @Test void testRemoveMovie() throws ServerException
  {
    viewModel.removeMovie(setup.getMovie());
    assertEquals(setup.getMovieList().size()-1, viewModel.getAllMovies().size());
  }

  @Test void testRemoveMovieIsNull(){
    assertThrows(NullPointerException.class, () -> viewModel.removeMovie(null));
  }

  @Test void removeMovieThatDoesNotExist(){
    assertThrows(ServerException.class, () -> viewModel.removeMovie(new Movie("Yikers")));
  }


}