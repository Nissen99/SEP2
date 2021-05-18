package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.dao.*;
import server.util.SeatNoCalculator;
import shared.exception.ServerException;
import shared.transferobjects.Seat;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelSeatTest
{
  private DAOTestSetup setup = new DAOTestSetup();
  private ViewModelSeat viewModel;
  private SeatDAO seatDAO = new SeatDAOImpl();
  private SeatNoCalculator seatNoCalculator = new SeatNoCalculator(
      setup.getHall().getHallNo(), setup.getHall().getMaxSeatsInRow(),
      setup.getHall().getMaxRows());
  private BookingSpecDAO bookingSpecDAO = new BookingSpecDAOImpl();
  private Object IndexOutOfBoundsException;

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
    for (int i = 0; i < setup.getHall().getMaxRows() * setup.getHall().getMaxSeatsInRow(); i++)
    {
      Seat seat = new Seat();
      seat.setSeatNo(seatNoCalculator.calculateSeatNo());
      seatDAO.create(setup.getHall().addSeat(seat), setup.getHall());
    }
    bookingSpecDAO.create(setup.getBookingList().get(0), setup.getHall().getSeats().get(1));
    viewModel = new ViewModelSeat(setup.getShowing());
  }

  @Test void testIfWeGetOccupiedSeats() throws ServerException
  {
    assertEquals(setup.getHall().getSeats().get(1).getSeatNo(), viewModel.getOccupiedSeats().get(0).getSeatNo());
  }

  @Test void testIfBookingAdded() throws ServerException
  {
    setup.getClient().login(setup.getUserName(), setup.getPassword());
    viewModel.addSeat(setup.getHall().getSeats().get(2).getSeatNo());
    viewModel.addBooking();
    assertEquals(setup.getHall().getSeats().get(2).getSeatNo(), viewModel.getOccupiedSeats().get(1).getSeatNo());
  }

  @Test void testCurrentNumber() {
    viewModel.setCurrentNumber("A101");
    assertEquals(102, viewModel.getCurrentNumber());
  }

  @Test void testIfSeatIsOccupiedOnClick()
  {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      viewModel.checkIfSeatOccupiedOnClick(
          setup.getHall().getSeats().get(1).getSeatNo());
    });
  }

  @Test void testIfSeatIsOccupiedOnLoad() throws ServerException
  {
    viewModel.updateOccupiedSeatsList();
    assertTrue(viewModel.seatIsOccupiedOnLoad(setup.getHall().getSeats().get(1).getSeatNo()));
  }
}