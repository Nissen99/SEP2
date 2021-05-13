package server.dao;


import shared.exception.ServerException;
import shared.transferobjects.Hall;
import java.sql.SQLException;
import java.util.ArrayList;

public interface HallDAO
{
  Hall create(Hall hall) throws ServerException;
  Hall getHallByNumber(String hallNo) throws ServerException;
  ArrayList<String> getHallNumbers() throws ServerException;
}
