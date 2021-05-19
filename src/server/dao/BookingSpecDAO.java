package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.IBooking;
import shared.transferobjects.ISeat;
import shared.transferobjects.IShowing;

import java.util.ArrayList;

/**
 * Implementeres af BookingSpecDAOImpl
 */
public interface BookingSpecDAO
{
  void create(IBooking booking, ISeat seat) throws ServerException;
  ArrayList<ISeat> getOccupiedSeats(IShowing showing) throws ServerException;

}
