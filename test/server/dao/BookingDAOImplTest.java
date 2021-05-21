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
 /*
  ArrayList<IBooking> getAllBookings() throws ServerException;
  void removeBooking(IBooking booking) throws ServerException;
  */

  private DAOTestSetup setup = new DAOTestSetup();
  private BookingDAO bookingDAO = new BookingDAOImpl();

  @BeforeEach
  void setup() throws ServerException
  {
    setup.setup();
  }

  @Test
  void getAllBookingsGivesUsArrayOfAllBookings() throws ServerException
  {
    int listSize = setup.getBookingList().size();
    assertEquals(listSize, bookingDAO.getAllBookings().size());
  }

  @Test void getAllBookingsGiveUsRightElement() throws ServerException
  {
    IBooking booking = setup.getBookingList().get(0);
    assertTrue(bookingDAO.getAllBookings().contains(booking));
  }


  @Test
  void testCreate() throws ServerException
  {
    assertEquals(2, bookingDAO.create(setup.getShowing(), setup.getUser()).getBookingId());
  }

  @Test
  void createWithNotValidShowing(){

    IShowing showing = new Showing(0, setup.getMovie(),new Timestamp(System.currentTimeMillis() + 3600000), setup.getHall());
    assertThrows(ServerException.class, () -> bookingDAO.create(showing, setup.getUser()));
  }

  @Test
  void createWithNullAsShowing(){
    assertThrows(ServerException.class, () -> bookingDAO.create(null, setup.getUser()));
  }

  @Test
  void createWithNotValidUser(){
    IUser user = new User(0, "Mikkel", "IkkeMinMail@Hotmail.com", "PasswordDerErSikkert123");
    assertThrows(ServerException.class, () -> bookingDAO.create(setup.getShowing(), user));
  }

  @Test
  void createWithNullAsUser(){
    assertThrows(ServerException.class, () -> bookingDAO.create(setup.getShowing(), null));
  }

  /**
   * Denne test giver kun mening hvis vi ved det element vi fjerner ligger i listen
   * Så vi tjekker om det er i listen, og efter det er fjernet tjekker vi om det er fjernet
   */
  @Test
  void removeBookingRemovesRight() throws ServerException
  {
    boolean listContains = bookingDAO.getAllBookings().contains(setup.getBooking());

    bookingDAO.removeBooking(setup.getBooking());

    assertNotEquals(listContains, bookingDAO.getAllBookings().contains(setup.getBooking()));
  }

  @Test
  void removeBookingAsNull(){
    assertThrows(ServerException.class, () -> bookingDAO.removeBooking(null));
  }


  @Test void listAdjustsSizeOnCreate() throws ServerException
  {
    int listStartSize = bookingDAO.getAllBookings().size();
   bookingDAO.create(setup.getShowing(), setup.getUser());

    assertEquals(listStartSize + 1, bookingDAO.getAllBookings().size());
  }

  @Test
  void listAdjustsSizeOnRemove() throws ServerException
  {
    int listStartSize = bookingDAO.getAllBookings().size();
    bookingDAO.removeBooking(setup.getBooking());

    assertEquals(listStartSize - 1, bookingDAO.getAllBookings().size());
  }







}