package client.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;

import static org.junit.jupiter.api.Assertions.*;

class ClientModelMovieManagerTest
{
  private ModelTestSetup setup = new ModelTestSetup();
  private ClientModelMovie model = new ClientModelMovieManager(setup.getClient());

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test void testAddMovie() throws ServerException
  {
    assertEquals(setup.getMovie().getMovieTitle(), model.addMovie(setup.getMovie()).getMovieTitle());
  }

}