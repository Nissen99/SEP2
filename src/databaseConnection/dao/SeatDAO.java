package databaseConnection.dao;

import shared.transferobjects.Hall;
import shared.transferobjects.Seat;

public interface SeatDAO
{

  void create(Seat seat, Hall hall);
  void setSeatOccupiedStatus(Seat seat, boolean b);
  Seat getSeatBySeatNo(String seatNo);
}
