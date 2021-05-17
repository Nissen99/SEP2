package server.dao;






import shared.MINKODE;

import java.nio.charset.MalformedInputException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO
{
  private static Connection connection;

  protected static Connection getConnection() throws SQLException
  {


      connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/postgres?currentSchema=bioDatabase",
          "postgres", MINKODE.PASSWORD.password);

    return connection;
  }
}
