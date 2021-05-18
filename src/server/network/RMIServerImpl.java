package server.network;

import server.model.*;
import shared.exception.ServerException;
import shared.util.ENUM;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.ArrayList;

public class RMIServerImpl implements RMIServer, PropertyChangeListener
{
  private final ServerModel modelManager;
  private final ServerModelBooking modelBooking;
  private final ServerModelCreateUser modelCreateUser;
  private final ServerModelLogin modelLogin;
  private final ServerModelMovie modelMovie;
  private final ServerModelShowing modelShowing;
  private final ServerModelShowingList modelShowingList;


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

  public void startServer() throws ServerException
  {
    try
    {
      Registry registry = LocateRegistry.createRegistry(1099);
      registry.bind(String.valueOf(ENUM.BIOSERVER), this);
    }
    catch (RemoteException | AlreadyBoundException e)
    {
      e.printStackTrace();
      throw new ServerException("Server start went wrong");
    }

  }

  //SERVER
  @Override public ArrayList<IMovie> getMovieList() throws ServerException
  {
    return modelManager.getMovieList();
  }

  //BOOKING
  @Override public synchronized void addBooking(IShowing showing, IUser user,
      ArrayList<ISeat> seats) throws ServerException
  {
    modelBooking.addBooking(showing, user, seats);
  }

  @Override public void removeBooking(IBooking booking) throws ServerException

  {
    modelBooking.removeBooking(booking);
  }

  @Override public ArrayList<IBooking> getBookingList() throws ServerException

  {
    return modelBooking.getBookingList();
  }

  @Override public ArrayList<ISeat> getOccupiedSeats(IShowing showing)
      throws ServerException
  {
    return modelBooking.getOccupiedSeats(showing);
  }


  //MOVIE
   @Override public IMovie addMovie(IMovie movie)
       throws ServerException
   {
       return modelMovie.addMovie(movie);

  }

  @Override public void removeMovie(IMovie movie)
      throws ServerException
  {
      modelMovie.removeMovie(movie);
   }



  //SHOWINGLIST
  @Override public ArrayList<IShowing> getShowingList(IMovie movie)
      throws ServerException
  {
      return modelShowingList.getShowingList(movie);
    }


  //SHOWING
  @Override public IShowing addShowing(IShowing showing) throws ServerException
  {
    return modelShowing.addShowing(showing);

  }


  @Override public IHall getHallByNumber(String hallNo)
      throws ServerException
  {
   return modelShowing.getHallByNumber(hallNo);

      }

  @Override public ArrayList<Timestamp> getShowingTimesByHallNoAndDate(
      String hallNo, Timestamp timestamp) throws ServerException
  {

      return modelShowing.getShowingTimesByHallNoAndDate(hallNo, timestamp);
   }

  @Override public ArrayList<String> getHallNumbers()
      throws ServerException
  {
      return modelShowing.getHallNumbers();

  }


  @Override public void removeShowing(IShowing showing) throws ServerException
  {
    modelShowing.removeShowing(showing);
  }

  @Override public void removeCallBack(ClientCallBack clientCallBack)
  {
    clientCallBackArrayList.remove(clientCallBack);
    modelBooking.removePropertyChangeListener(this);
  }

  //CREATE USER
  @Override public void createUser(String userName, String email,String password)
      throws ServerException
  {

      modelCreateUser.createUser(userName,email,password);

  }



  //LOGIN
  @Override public IUser login(String userName, String password)
      throws ServerException
  {

      return modelLogin.login(userName,password);

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
        clientCallBack.update(evt);
      }
      catch (RemoteException e)
      {
          clientCallBacksThatWentWrong.add(clientCallBack);
      }
    }

    for (ClientCallBack clientCallBack : clientCallBacksThatWentWrong)
    {
      clientCallBackArrayList.remove(clientCallBack);
    }
  }
}
