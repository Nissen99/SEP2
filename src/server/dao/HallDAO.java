package server.dao;


import shared.transferobjects.Hall;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HallDAO
{
  Hall create(Hall hall) throws SQLException;
  Hall getHallByNumber(String hallNo) throws SQLException;

  ArrayList<String> getHallNumbers() throws SQLException;
}
