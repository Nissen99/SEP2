package client.view.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.dao.*;
import server.util.SeatNoCalculator;
import shared.exception.ServerException;
import shared.transferobjects.*;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelSeatTest
{
  private VMTestSetup setup = new VMTestSetup();
  private ViewModelSeat viewModel;
  private SeatDAO seatDAO = new SeatDAOImpl();
  private SeatNoCalculator seatNoCalculator = new SeatNoCalculator(
      setup.getHall().getHallNo(), setup.getHall().getMaxSeatsInRow(),
      setup.getHall().getMaxRows());
  private BookingSpecDAO bookingSpecDAO = new BookingSpecDAOImpl();


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
    viewModel = new ViewModelSeat();
    viewModel.setShowing(setup.getShowing());
    viewModel.updateOccupiedSeatsList();
    setup.getClient().login(setup.getUserName(), setup.getPassword());

  }

  @Test void testIfWeGetOccupiedSeats() throws ServerException
  {
    assertEquals(setup.getHall().getSeats().get(1).getSeatNo(), viewModel.getOccupiedSeats().get(0).getSeatNo());
  }

  @Test void testIfBookingAdded() throws ServerException
  {
    viewModel.addSeat(setup.getHall().getSeats().get(2).getSeatNo());
    viewModel.addBooking();

    assertEquals(setup.getHall().getSeats().get(2).getSeatNo(), viewModel.getOccupiedSeats().get(1).getSeatNo());
  }

  @Test void whenSeatsBookedOccupiedSeatListGrows() throws ServerException
  {
    int seatsSize = viewModel.getOccupiedSeats().size();
    viewModel.addSeat(setup.getHall().getSeats().get(10).getSeatNo());
    viewModel.addSeat(setup.getHall().getSeats().get(11).getSeatNo());
    viewModel.addSeat(setup.getHall().getSeats().get(12).getSeatNo());
    viewModel.addBooking();

    assertEquals(seatsSize + 3, viewModel.getOccupiedSeats().size());

  }

  @Test void ifEmptySeatListInAddBooking(){
    assertThrows(ServerException.class, () -> viewModel.addBooking());
  }

  /**
   * Denne test går ud fra at {@link #ifEmptySeatListInAddBooking()}
   * virker
   */
  @Test void testIfClearBookingListClearsBookingList(){
    viewModel.addSeat(setup.getHall().getSeats().get(10).getSeatNo());
    viewModel.addSeat(setup.getHall().getSeats().get(11).getSeatNo());
    viewModel.clearBookingList();
    assertThrows(ServerException.class, () -> viewModel.addBooking());
  }


  @Test void testIfSeatNotInDatabaseCanBeAddetToList()
  {
    viewModel.addSeat("3001");
    assertThrows(ServerException.class, () -> viewModel.addBooking());
  }

  @Test void ifSeatNoIsNull(){
    viewModel.addSeat(null);
    assertThrows(ServerException.class, () -> viewModel.addBooking());
  }


  @Test void testSetNewIdCountsIdUp() {
    assertEquals("A102", viewModel.setNewId("A101"));
  }

  /**
   * Når sædet man vælger ikke er optaget
   */
  @Test void seatIsOccupiedDoesNotThrow(){
    assertDoesNotThrow(() -> setup.getHall().getSeats().get(10));
  }

  /**
   * Når sædet man vælger er optaget
   */
  @Test void testIfSeatIsOccupiedOnClick()
  {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      viewModel.checkIfSeatOccupiedOnClick(
          setup.getHall().getSeats().get(1).getSeatNo());
    });
  }


  @Test void testIfSeatIsOccupiedOnLoad()
  {
    assertTrue(viewModel.seatIsOccupied(setup.getHall().getSeats().get(1).getSeatNo()));
  }

  /**
   * Vi vil teste om seatIsOccupied returnere relevant og om vores updateOccupiedSeatsList
   * giver os det nyeste information.
   *
   * Vi kan ikke isoleret teste updateOccupiedSeatsList, da der ikke er nogen måde at
   * få fat i listen på.
   */
  @Test void ifNewWeCanFindNewBookingInOccupiedList() throws ServerException
  {

    assertFalse(viewModel.seatIsOccupied(setup.getHall().getSeats().get(10).getSeatNo()));

    bookingSpecDAO.create(setup.getBooking(), setup.getHall().getSeats().get(10));
    viewModel.updateOccupiedSeatsList();

    assertTrue(viewModel.seatIsOccupied(setup.getHall().getSeats().get(10).getSeatNo()));
  }


  @Test void ifSetSelectedShowingGetsNull(){
    viewModel.setShowing(null);
    assertThrows(NullPointerException.class, () -> viewModel.getOccupiedSeats());
  }

@Test void getChoiceListBoundaryTest(){
  assertEquals(1, viewModel.getChoiceList().get(0));
  assertEquals(7, viewModel.getChoiceList().get(6));
  assertEquals(14, viewModel.getChoiceList().get(13));
  assertThrows(IndexOutOfBoundsException.class, () -> viewModel.getChoiceList().get(-1));
  assertThrows(IndexOutOfBoundsException.class, () -> viewModel.getChoiceList().get(14));

}

}