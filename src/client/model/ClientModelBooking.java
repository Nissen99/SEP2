package client.model;

import shared.transferobjects.*;
import shared.util.PropertyChangeSubject;
import shared.exception.ServerException;

import java.util.ArrayList;

/**
 * Implementeret af ClientModelBookingManager
 */

public interface ClientModelBooking extends ClientModelShowingList,
    PropertyChangeSubject
{
  void addBooking(IShowing showing,  ArrayList<ISeat> seats)
      throws ServerException;
  void removeBooking(IBooking booking) throws ServerException;
  ArrayList<ISeat> getOccupiedSeats(IShowing showing) throws ServerException;
  ArrayList<IBooking> getBookingList() throws ServerException;
}
