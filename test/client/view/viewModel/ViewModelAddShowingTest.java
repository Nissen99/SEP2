package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.dao.HallDAO;
import server.dao.HallDAOImpl;
import server.dao.ResetDAO;
import server.dao.ResetDAOImpl;
import shared.exception.ServerException;
import shared.transferobjects.Hall;
import shared.transferobjects.IHall;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ViewModelAddShowingTest
{
  private ViewModelAddShowing viewModel = new ViewModelAddShowing();
  private ResetDAO resetDAO = new ResetDAOImpl();
  private VMTestSetup setup = new VMTestSetup();
  private HallDAO hallDAO = new HallDAOImpl();
  private IHall hall;
  private Timestamp time = new Timestamp(121, 6, 15,
      13, 30, 1, 0);

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test void testIfShowingIsCreatedWithCorrectInfo()
  {
    assertEquals(1, setup.getShowing().getId());
    assertEquals(setup.getMovieTitle(),
        setup.getShowing().getMovie().getMovieTitle());
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
    setup.getTime().setNanos(1);
  }


  @Test void testGetChoiceList() throws ServerException
  {
    assertEquals(setup.getHall().getHallNo(), viewModel.getChoiceList().get(0));
  }


}