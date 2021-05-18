package client.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;

import static org.junit.jupiter.api.Assertions.*;

class ClientModelShowingManagerTest
{
  private ModelTestSetup setup = new ModelTestSetup();
  private ClientModelShowing model = new ClientModelShowingManager(setup.getClient());

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test void testAddShowing() throws ServerException
  {
    assertEquals(setup.getShowing().getTime(), model.addShowing(setup.getShowing()).getTime());
  }

}