package server;

import databaseConnection.dao.*;
import server.model.ServerModel;
import server.model.ServerModelManager;
import server.network.RMIServerImpl;
import shared.transferobjects.Hall;
import shared.transferobjects.Movie;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class StartServer
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException, SQLException
  {

    RMIServerImpl server = new RMIServerImpl(new ServerModelManager());
    server.startServer();
    setup();
    System.out.println("Server is running");
  }

  private static void setup() throws SQLException
  {
    MovieDAO movieDAO = new MovieDAOImpl();
    movieDAO.create("Jackass");
    Movie movie = new Movie(1,"Jackass");
    SeatDAO seatDAO = new SeatDAOImpl();
    Hall hall = new Hall("A", 16, 14);
    HallDAO hallDAO = new HallDAOImpl();
    hallDAO.create(hall);
    for (int i = 0; i < hall.getMaxRows() * hall.getMaxSeatsInRow(); i++)
    {
      Seat seat = new Seat();
      seatDAO.create(hall.addSeat(seat), hall);
    }

    Timestamp time = new Timestamp(System.currentTimeMillis());
    Showing showing = new Showing(1, movie, time, hall);
    ShowingDAO showingDAO = new ShowingDAOImpl();
    showingDAO.create(showing);
  }
}
