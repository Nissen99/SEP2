package databaseConnection.dao;

import shared.Hall;
import shared.Seat;

public interface SeatDAO
{

  void create(Seat seat, Hall hall);
  void setSeatOccupiedStatus(Seat seat, boolean b);
}
