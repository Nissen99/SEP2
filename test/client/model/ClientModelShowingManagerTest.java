package client.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;

import java.sql.Timestamp;

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

  @Test void testTimeOverLaps() {
    assertThrows(ServerException.class, () -> {
      model.checkIfTimeOverlaps(setup.getHall().getHallNo(), setup.getTime());
    });
  }

  @Test void testTimeOverlapsBy3Hours()
  {
    setup.getTime().setHours(setup.getTime().getHours() + 3);
    assertDoesNotThrow(() -> model.checkIfTimeOverlaps(setup.getHall().getHallNo(), setup.getTime()));
  }

  @Test void testTimeOverlapsBy2hours59minutes() {
    setup.getTime().setHours(setup.getTime().getHours() + 2);
    setup.getTime().setMinutes(setup.getTime().getMinutes() + 59);
    assertThrows(ServerException.class, () -> {
      model.checkIfTimeOverlaps(setup.getHall().getHallNo(), setup.getTime());
    });
  }

  @Test void testTimeUnderlapsBy3Hours() {
    setup.getTime().setHours(setup.getTime().getHours() - 3);
    assertDoesNotThrow(() -> model.checkIfTimeOverlaps(setup.getHall().getHallNo(), setup.getTime()));
  }

  @Test void testTimeUnderlapsBy2Hours59Minutes() {
    setup.getTime().setHours(setup.getTime().getHours() - 2);
    setup.getTime().setMinutes(setup.getTime().getMinutes() - 59);
    assertThrows(ServerException.class, () -> {
      model.checkIfTimeOverlaps(setup.getHall().getHallNo(), setup.getTime());
    });
  }



}