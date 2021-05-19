package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.IHall;
import shared.transferobjects.ISeat;

/**
 * Implementeret af SeatDAOImpl
 */
public interface SeatDAO
{
  void create(ISeat seat, IHall hall) throws ServerException;
  ISeat getSeatBySeatNo(String seatNo) throws ServerException;
}
