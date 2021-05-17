package client.view.viewModel;

import client.core.ClientFactory;
import client.network.RMIClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.dao.*;
import server.util.SeatNoCalculator;
import shared.exception.ServerException;
import shared.transferobjects.Seat;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewModelSeatTest
{
  private DAOTestSetup setup = new DAOTestSetup();
  private ViewModelSeat viewModel;
  private SeatDAO seatDAO = new SeatDAOImpl();
  private SeatNoCalculator seatNoCalculator = new SeatNoCalculator(setup.getHall().getHallNo(),setup.getHall().getMaxSeatsInRow(),setup.getHall().getMaxRows());
  private RMIClient client = ClientFactory.getInstance().getClient();
  private BookingSpecDAO bookingSpecDAO = new BookingSpecDAOImpl();




  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
    for (int i = 0; i < setup.getHall().getMaxRows() * setup.getHall().getMaxSeatsInRow(); i++)
    {
      Seat seat = new Seat();
      seat.setSeatNo(seatNoCalculator.calculateSeatNo());
      seatDAO.create(setup.getHall().addSeat(seat),setup.getHall());


    }

    viewModel = new ViewModelSeat(setup.getShowing());
   bookingSpecDAO.create(setup.getBookingList().get(0),setup.getHall().getSeats().get(1));
  }

  @Test
  void testIfWeGetOccupiedSeats() throws ServerException
  {
    assertEquals(setup.getHall().getSeats().get(1).getSeatNo(),viewModel.getOccupiedSeats().get(0).getSeatNo());

  }

@Test
  void testIfBookingAdded() throws ServerException
{

  client.login(setup.getUserName(),setup.getPassword());
  viewModel.addSeat(setup.getHall().getSeats().get(1).getSeatNo());
  viewModel.addBooking();
  assertEquals(setup.getBookingList().size(),viewModel.getOccupiedSeats().size());

}


}