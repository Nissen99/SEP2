package server.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import shared.transferobjects.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


class BookingDAOImplTest
{
  private DAOTestSetup setup = new DAOTestSetup();
  private BookingDAO bookingDAO = new BookingDAOImpl();

  @BeforeEach
  public void setup() throws ServerException
  {
    setup.setup();
  }

  @Test
  public void testCreate() throws ServerException
  {
    assertEquals(2, bookingDAO.create(setup.getShowing(), setup.getUser()).getBookingId());
  }

//  @Test
//  public void testGetOccupiedSeats() throws SQLException, ServerException
//  {
//    setUpGood("Y");
//
//    ArrayList<Seat> seatArrayList = new ArrayList<>();
//    Seat seat = new Seat();
//    seat.setSeatNo("Y101");
//    Seat seat2 = new Seat();
//    seat2.setSeatNo("Y102");
//    Seat seat3 = new Seat();
//    seat3.setSeatNo("Y103");
//
//    seatArrayList.add(seat);
//    seatArrayList.add(seat2);
//    seatArrayList.add(seat3);
//
//    bookingDAO.create(showing, user);
//    bookingDAO.create(showing, user);
//    bookingDAO.create(showing, user);
//
//    assertEquals(seatArrayList.get(0).getSeatNo(),
//        bookingDAO.getOccupiedSeats(showing).get(0).getSeatNo());
//    assertEquals(seatArrayList.get(1).getSeatNo(),
//        bookingDAO.getOccupiedSeats(showing).get(1).getSeatNo());
//    assertEquals(seatArrayList.get(2).getSeatNo(),
//        bookingDAO.getOccupiedSeats(showing).get(2).getSeatNo());
//
//  }
}