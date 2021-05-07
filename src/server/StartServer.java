package server;

import databaseConnection.dao.*;
import server.model.ServerModel;
import server.model.ServerModelManager;
import server.network.RMIServerImpl;
import shared.transferobjects.*;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class StartServer
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException, SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
    RMIServerImpl server = new RMIServerImpl(new ServerModelManager());
    server.startServer();
    setup();
    System.out.println("Server is running");
  }

  private static void setup() throws SQLException
  {
    HallDAO hallDAO = new HallDAOImpl();

    if (hallDAO.getHallByNumber("A") == null){
    MovieDAO movieDAO = new MovieDAOImpl();
    movieDAO.create("Jackass");
    Movie movie = new Movie(1,"Jackass");
    SeatDAO seatDAO = new SeatDAOImpl();
    Hall hall = new Hall("A", 16, 14);
    hallDAO.create(hall);
    SeatNoCalculator seatNoCalculator = new SeatNoCalculator(hall.getHallNo(),
        hall.getMaxSeatsInRow(), hall.getMaxRows());
    for (int i = 0; i < hall.getMaxRows() * hall.getMaxSeatsInRow(); i++)
    {
      Seat seat = new Seat();
      seat.setSeatNo(seatNoCalculator.calculateSeatNo());
      seatDAO.create(hall.addSeat(seat), hall);
    }

    Timestamp time = new Timestamp(System.currentTimeMillis());
    Showing showing = new Showing(1, movie, time, hall);
    ShowingDAO showingDAO = new ShowingDAOImpl();
    showingDAO.create(showing);
  }}
}
