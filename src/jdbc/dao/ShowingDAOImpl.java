package jdbc.dao;

import shared.transferobjects.Showing;

import java.sql.SQLException;

public class ShowingDAOImpl extends BaseDAO implements ShowingDAO
{
  private static ShowingDAOImpl instance;
  public static synchronized ShowingDAOImpl getInstance() throws SQLException
  {
    if (instance == null) {
      instance = new ShowingDAOImpl();
    }
    return instance;
  }
}
