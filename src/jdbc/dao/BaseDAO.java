package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO
{
  private static Connection connection;

  protected static Connection getConnection() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
    if (connection == null)
    {
      connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/postgres?currentSchema=bioDatabase",
          "postgres", "123456789");
    }
    return connection;
  }
}
