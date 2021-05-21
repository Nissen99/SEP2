package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.IBooking;
import shared.transferobjects.IShowing;
import shared.transferobjects.IUser;

import java.util.ArrayList;

/**
 * Implementeres af BookingDAOImpl
 */
public interface BookingDAO
{
  IBooking create(IShowing showing, IUser user) throws ServerException;
  ArrayList<IBooking> getAllBookings() throws ServerException;
  void removeBooking(IBooking booking) throws ServerException;

}
