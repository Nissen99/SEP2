package server;

import server.dao.*;
import server.model.*;
import server.network.RMIServerImpl;
import server.util.SeatNoCalculator;
import shared.exception.ServerException;
import shared.transferobjects.*;
import java.rmi.RemoteException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;

public class StartServer
{
  public static void main(String[] args)
      throws RemoteException, SQLException,
      ServerException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());

    ServerModelBooking serverModelBooking = new ServerModelBookingManager();
    ServerModelCreateUser serverModelCreateUser = new ServerModelCreateUserManager();
    ServerModelLogin serverModelLogin = new ServerModelLoginManger();
    ServerModelMovie serverModelMovie = new ServerModelMovieManager();
    ServerModelShowing serverModelShowing = new ServerModelShowingManager();

    RMIServerImpl server = new RMIServerImpl(serverModelBooking,
        serverModelCreateUser, serverModelLogin, serverModelMovie,
        serverModelShowing);

    server.startServer();
    setup();
    System.out.println("Server is running");
  }

  private static void setup() throws ServerException
  {
    HallDAO hallDAO = new HallDAOImpl();

    if (hallDAO.getHallByNumber("A") == null)
    {
      MovieDAO movieDAO = new MovieDAOImpl();
      movieDAO.create("Jackass");
      IMovie movie = new Movie(1, "Jackass");
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

      Timestamp time = new Timestamp(System.currentTimeMillis() + 36000000);
      IShowing showing = new Showing(1, movie, time, hall);
      ShowingDAO showingDAO = new ShowingDAOImpl();
      showingDAO.create(showing);
    }
  }
}
