package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.Booking;
import shared.transferobjects.Seat;

public interface BookingSpecDAO
{
  void create(Booking booking, Seat seat) throws ServerException;
}
