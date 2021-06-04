package server;

import server.dao.*;
import server.util.SeatNoCalculator;
import shared.exception.ServerException;
import shared.transferobjects.*;

import java.sql.Timestamp;

public class SetUp
{

  public static void setUp() throws ServerException
  {
    HallDAO hallDAO = new HallDAOImpl();

    if (hallDAO.getHallByNumber("A") == null)
    {
      MovieDAO movieDAO = new MovieDAOImpl();

     IMovie jackass = movieDAO.create("Jackass");
     IMovie theGoldenGun = movieDAO.create("The Golden Gun");
     IMovie avatar = movieDAO.create("Avatar");

      IHall hall = makeHall(hallDAO);

      Timestamp time1 = new Timestamp(System.currentTimeMillis() + 36000000);
      Timestamp time2 = new Timestamp(System.currentTimeMillis() + 36000000 * 2);
      Timestamp time3 = new Timestamp(System.currentTimeMillis() + 36000000 * 3);

      IShowing showing1 = new Showing(jackass, time1, hall);
      IShowing showing2 = new Showing(theGoldenGun, time2, hall);
      IShowing showing3 = new Showing(avatar, time3, hall);
      ShowingDAO showingDAO = new ShowingDAOImpl();

      showingDAO.create(showing1);
      showingDAO.create(showing2);
      showingDAO.create(showing3);
    }
  }

   private static IHall makeHall(
      HallDAO hallDAO) throws ServerException
  {
    SeatDAO seatDAO = new SeatDAOImpl();
    IHall hall = new Hall("A", 16, 14);
    hallDAO.create(hall);

    SeatNoCalculator seatNoCalculator = new SeatNoCalculator(hall.getHallNo(),
        hall.getMaxSeatsInRow(), hall.getMaxRows());
    for (int i = 0; i < hall.getMaxRows() * hall.getMaxSeatsInRow(); i++)
    {
      ISeat seat = new Seat();
      seat.setSeatNo(seatNoCalculator.calculateSeatNo());
      seatDAO.create(hall.addSeat(seat), hall);
    }
    return hall;
  }
}
