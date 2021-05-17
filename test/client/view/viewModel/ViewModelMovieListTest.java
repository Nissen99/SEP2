package client.view.viewModel;

import client.network.RMIClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelMovieListTest
{


  private DAOTestSetup setup = new DAOTestSetup();
  private ViewModelMovieList viewModel = new ViewModelMovieList();



  @BeforeEach
  void setup() throws ServerException
  {
    setup.setup();
  }

   @Test
  void testIfWeGetAllMovies() throws ServerException
   {

     assertEquals(setup.getMovieList().size(),viewModel.getAllMovies().size());

   }



}