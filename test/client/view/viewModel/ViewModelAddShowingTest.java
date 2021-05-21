package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.dao.*;
import shared.exception.ServerException;
import shared.transferobjects.Hall;
import shared.transferobjects.IHall;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ViewModelAddShowingTest
{
  private ViewModelAddShowing viewModel = new ViewModelAddShowing();
  private ResetDAO resetDAO = new ResetDAOImpl();
  private VMTestSetup setup = new VMTestSetup();
  private HallDAO hallDAO = new HallDAOImpl();
  private IHall hall;
  private Timestamp timestamp = new Timestamp(121, 6, 16,
      13, 30, 0, 0);
  private ShowingDAO showingDAO = new ShowingDAOImpl();
  LocalDate date = LocalDate.of(2021, 7, 16);
  LocalTime time = LocalTime.of(13, 30);

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
    viewModel.hallNoProperty().setValue("A");
    viewModel.setSelectedMovie(setup.getMovie());
  }

  @Test void testIfShowingIsCreatedWithCorrectInfo()
  {
    assertEquals(1, setup.getShowing().getId());
    assertEquals(setup.getMovieTitle(), setup.getShowing().getMovie().getMovieTitle());
    assertEquals(setup.getTime(), setup.getShowing().getTimestamp());
    assertEquals("A", setup.getShowing().getHall().getHallNo());
  }

  @Test void testIfItsTheRightHall() throws ServerException
  {
    assertEquals(setup.getHall().getHallNo(),
        viewModel.getHallByNumber("A").getHallNo());
  }

  @Test void testIfManyHallsAreCreated() throws ServerException
  {
    assertEquals(setup.getHall().getHallNo(), viewModel.getHallByNumber("A").getHallNo());
    ArrayList<IHall> hallList = new ArrayList<>();

    hallList.add(hallDAO.create(hall = new Hall("B", 2, 2)));
    hallList.add(hallDAO.create(hall = new Hall("C", 2, 2)));
    hallList.add(hallDAO.create(hall = new Hall("D", 2, 2)));
    hallList.add(hallDAO.create(hall = new Hall("E", 2, 2)));

    assertEquals(hallList.size()+setup.getHallList().size(), viewModel.getChoiceList().size());
  }

  @Test void testIfZeroHallIsCreated() throws ServerException
  {
    resetDAO.reset();
    assertEquals(0,viewModel.getChoiceList().size());
  }

  @Test void testGetChoiceList() throws ServerException
  {
    assertEquals(setup.getHall().getHallNo(), viewModel.getChoiceList().get(0));
  }

  @Test void testAddShowing() throws ServerException
  {
    viewModel.localDatePropertyProperty().setValue(date);
    viewModel.localTimePropertyProperty().setValue(time);
    viewModel.addShowing();
    assertEquals(time.toString(), showingDAO.getAllShowings(setup.getMovie()).get(1).getTime());
  }

  @Test void testTimeOverLaps() {
    assertThrows(IllegalArgumentException.class, () -> {
      viewModel.checkIfTimeOverlaps(setup.getTime());
    });
  }

  @Test void testTimeOverlapsBy3Hours()
  {
    setup.getTime().setHours(setup.getTime().getHours() + 3);
    assertDoesNotThrow(() -> viewModel.checkIfTimeOverlaps(setup.getTime()));
  }

  @Test void testTimeOverlapsBy2hours59minutes() {
    setup.getTime().setHours(setup.getTime().getHours() + 2);
    setup.getTime().setMinutes(setup.getTime().getMinutes() + 59);
    assertThrows(IllegalArgumentException.class, () -> {
      viewModel.checkIfTimeOverlaps(setup.getTime());
    });

  }

  @Test void testTimeUnderlapsBy3Hours() {
    setup.getTime().setHours(setup.getTime().getHours() - 3);
    assertDoesNotThrow(() -> viewModel.checkIfTimeOverlaps(setup.getTime()));
  }

  @Test void testTimeUnderlapsBy2Hours59Minutes() {
    setup.getTime().setHours(setup.getTime().getHours() - 2);
    setup.getTime().setMinutes(setup.getTime().getMinutes() - 59);
    assertThrows(IllegalArgumentException.class, () -> {
      viewModel.checkIfTimeOverlaps(setup.getTime());
    });
  }
}