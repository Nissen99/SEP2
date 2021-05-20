package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import static org.junit.jupiter.api.Assertions.*;

class ViewModelMovieListTest
{


  private VMTestSetup setup = new VMTestSetup();
  private ViewModelMovieList viewModel = new ViewModelMovieList();



  @BeforeEach
  void setup() throws ServerException
  {
    setup.setup();
    viewModel.setSelectedMovie(setup.getMovie());
  }

   @Test
  void testIfWeGetAllMovies() throws ServerException
   {
     assertEquals(setup.getMovieList().size(), viewModel.getAllMovies().size());
   }

   @Test void ifWeGetTheRightMovies() throws ServerException
   {
     assertEquals(setup.getMovieList().get(0).getMovieId(), viewModel.getAllMovies().get(0).getMovieId());
   }

   @Test void testSelectedMovieAndGetMovie() {
    assertEquals(setup.getMovie().getMovieTitle(), viewModel.getSelectedMovie().getMovieTitle());
   }

   @Test void selectedMoveIsNull(){
    assertThrows(NullPointerException.class, () -> viewModel.setSelectedMovie(null));
   }



}