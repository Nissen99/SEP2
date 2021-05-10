package server.dao;

import shared.transferobjects.Hall;
import shared.transferobjects.Seat;

public interface SeatDAO
{

  void create(Seat seat, Hall hall);
  Seat getSeatBySeatNo(String seatNo);
}
