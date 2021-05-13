package server.network;

import javafx.application.Platform;
import server.model.*;
import shared.exception.ServerException;
import shared.util.ENUM;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.transferobjects.*;
import javax.security.auth.login.LoginException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class RMIServerImpl implements RMIServer, PropertyChangeListener
{
  private ServerModel modelManager;
  private ServerModelBooking modelBooking;
  private ServerModelCreateUser modelCreateUser;
  private ServerModelLogin modelLogin;
  private ServerModelMovie modelMovie;
  private ServerModelShowing modelShowing;
  private ServerModelShowingList modelShowingList;


  private ArrayList<ClientCallBack> clientCallBackArrayList = new ArrayList<>();

  public RMIServerImpl(ServerModel modelManager, ServerModelBooking modelBooking, ServerModelCreateUser modelCreateUser,
      ServerModelLogin modelLogin, ServerModelMovie modelMovie, ServerModelShowing modelShowing,
      ServerModelShowingList modelShowingList) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.modelManager = modelManager;
    this.modelBooking = modelBooking;
    this.modelCreateUser = modelCreateUser;
    this.modelLogin = modelLogin;
    this.modelMovie = modelMovie;
    this.modelShowing = modelShowing;
    this.modelShowingList = modelShowingList;
    modelBooking.addPropertyChangeListener(this::propertyChange);
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind(String.valueOf(ENUM.BIOSERVER), this);

  }

  //SERVER
  @Override public ArrayList<Movie> getMovieList() throws SQLException
  {
    return modelManager.getMovieList();
  }

  //BOOKING
  @Override public Booking addBooking(Showing showing,User user,
      ArrayList<Seat> seats) throws ServerException
  {
    return modelBooking.addBooking(showing, user, seats);

  }

  @Override public void removeBooking(Booking booking)
      throws SQLException
  {
    modelBooking.removeBooking(booking);
  }

  @Override public ArrayList<Booking> getBookingList()
      throws SQLException
  {
    return modelBooking.getBookingList();
  }

  @Override public ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws SQLException
  {
    return modelBooking.getOccupiedSeats(showing);
  }


  //MOVIE
   @Override public Movie addMovie(Movie movie)
       throws SQLException, ServerException
   {
    try
    {
      return modelMovie.addMovie(movie);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException();
    }
  }

  @Override public void removeMovie(Movie movie)
      throws RemoteException, SQLException
  {
    modelMovie.removeMovie(movie);
  }



  //SHOWINGLIST
  @Override public ArrayList<Showing> getShowingList(Movie movie)
      throws SQLException, ServerException
  {
    try
    {
      return modelShowingList.getShowingList(movie);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException();
    }
  }


  //SHOWING
  @Override public Showing addShowing(Showing showing) throws SQLException, ServerException
  {
    try
    {
      return modelShowing.addShowing(showing);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException();
    }
  }


  @Override public Hall getHallByNumber(String hallNo)
      throws SQLException, ServerException
  {
    try
    {
      return modelShowing.getHallByNumber(hallNo);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException();
    }
  }

  @Override public ArrayList<Timestamp> getShowingTimesByHallNoAndDate(
      String hallNo, Timestamp timestamp) throws SQLException, ServerException
  {
    try
    {
      return modelShowing.getShowingTimesByHallNoAndDate(hallNo, timestamp);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    throw new ServerException();
    }

  }

  @Override public ArrayList<String> getHallNumbers()
      throws SQLException, ServerException
  {
    try
    {
      return modelShowing.getHallNumbers();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException();
    }
  }


  @Override public void removeShowing(Showing showing) throws SQLException
  {
    modelShowing.removeShowing(showing);
  }




  //CREATE USER
  @Override public void createUser(String userName, String email,String password)
      throws SQLException, ServerException
  {
    try
    {
      modelCreateUser.createUser(userName,email,password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException();
    }
  }



  //LOGIN
  @Override public User login(String userName, String password)
      throws LoginException, ServerException
  {
    try
    {
      return modelLogin.login(userName,password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException();
    }
  }



  @Override public void registerCallback(ClientCallBack client)
  {
    clientCallBackArrayList.add(client);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    ArrayList<ClientCallBack> clientCallBacksThatWentWrong = new ArrayList<>();
    for (ClientCallBack clientCallBack : clientCallBackArrayList)
    {
      try
      {
        System.out.println("Call back client fires before");
        clientCallBack.update(evt);
        System.out.println("Call back client fires after");
      }
      catch (RemoteException e)
      {
        System.out.println("Vi Kom i Catch på callBack");

          clientCallBacksThatWentWrong.add(clientCallBack);

      }
    }

    for (ClientCallBack clientCallBack : clientCallBacksThatWentWrong)
    {
      clientCallBackArrayList.remove(clientCallBack);
    }
  }
}
