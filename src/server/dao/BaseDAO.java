package server.dao;


import server.util.MINKODE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO
{
  private static Connection connection;

  /**
   * Tjekker på url efter databasen og logger ind med username og password
   * @return Connetion til databasen
   * @throws SQLException Hvis forbindelse ikke kan oprettes
   */
  protected Connection getConnection() throws SQLException
  {
      connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/postgres?currentSchema=bioDatabase",
          "postgres", MINKODE.PASSWORD.password);

    return connection;
  }
}
