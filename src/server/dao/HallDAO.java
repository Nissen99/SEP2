package server.dao;


import shared.exception.ServerException;
import shared.transferobjects.IHall;

import java.util.ArrayList;

/**
 * Implementeres af HallDAOImpl
 */
public interface HallDAO
{
  IHall create(IHall hall) throws ServerException;
  IHall getHallByNumber(String hallNo) throws ServerException;
  ArrayList<String> getHallNumbers() throws ServerException;
}
