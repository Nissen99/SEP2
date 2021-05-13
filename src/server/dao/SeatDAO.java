package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.Hall;
import shared.transferobjects.Seat;

public interface SeatDAO
{

  void create(Seat seat, Hall hall) throws ServerException;
  Seat getSeatBySeatNo(String seatNo) throws ServerException;
}
