package server;

import server.model.*;
import server.network.RMIServerImpl;
import shared.exception.ServerException;
import java.rmi.RemoteException;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    SetUp.setUp(); //Ønskes der ikke dummy data udkommentere dette.
    System.out.println("Server is running");
  }


}
