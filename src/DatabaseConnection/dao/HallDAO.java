package DatabaseConnection.dao;


import shared.Hall;

import java.sql.SQLException;

public interface HallDAO
{
  Hall create(Hall hall) throws SQLException;

}
