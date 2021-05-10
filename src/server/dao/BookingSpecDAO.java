package server.dao;

import shared.transferobjects.Booking;
import shared.transferobjects.Seat;

public interface BookingSpecDAO
{
  void create(Booking booking, Seat seat);
}
