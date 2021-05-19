package server.model;

import shared.exception.ServerException;

import shared.transferobjects.IBooking;
import shared.transferobjects.ISeat;
import shared.transferobjects.IShowing;
import shared.transferobjects.IUser;
import shared.util.PropertyChangeSubject;

import java.util.ArrayList;

/**
 * Implementeres af ServerModelBookingManager
 */
public interface ServerModelBooking extends PropertyChangeSubject
{
  void addBooking(IShowing showing, IUser user, ArrayList<ISeat> seats)
      throws ServerException;
  void removeBooking(IBooking booking) throws ServerException;
  ArrayList<ISeat> getOccupiedSeats(IShowing showing) throws ServerException;
  ArrayList<IBooking> getBookingList() throws ServerException;
}
