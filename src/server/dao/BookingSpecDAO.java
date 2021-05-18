package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.IBooking;
import shared.transferobjects.ISeat;

public interface BookingSpecDAO
{
  void create(IBooking booking, ISeat seat) throws ServerException;
}
