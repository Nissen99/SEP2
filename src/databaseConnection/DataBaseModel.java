package databaseConnection;

import java.sql.*;

public class DataBaseModel
{

  private Connection connection;
  private PreparedStatement userInsertStatement;
  private PreparedStatement deleteUser;



  public DataBaseModel()
  {
    setConnection();
    userInsertStatement = prepareInsertUserStatement();
    deleteUser= prepareDeleteUserStatement();

  }


  //Conection to postgres
  private void setConnection()
  {
    String driver = "org.postgresql.Driver";
    String url ="jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "12345";
    connection = null;

    // now we are loading the JDBC driver.
    try
    {
      Class.forName(driver);
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }

    try
    {
      connection= DriverManager.getConnection(url,user,password);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

// creates the schema
  public void createSep2Schema()
  {
    String sql = "CREATE SCHEMA IF NOT EXISTS \"Sep2\";";
    try {
      Statement statement = connection.createStatement();
      statement.execute(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }



  // Creating a empty user table
  public void createUserTable()
  {
    String sql = "CREATE TABLE IF NOT EXISTS\"Sep2\".User (" +
        "   UserID varchar(30) NOT NULL PRIMARY KEY," +
        " firstName varchar(30) NOT NULL," +
        "lastName varchar(30) NOT NULL," +
        ");";
    try {
      Statement statement = connection.createStatement();
      statement.execute(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }



  public PreparedStatement prepareDeleteEmployee() {
    String preparedSql = "DELETE FROM \"Sep2\".employee " +
        "where employeeID = ? ;";

    deleteUser = null;

    try {
      deleteUser = connection.prepareStatement(preparedSql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return deleteUser;
  }

  //Prepares a user statement to improve DB performance for simple queries that are used multiple times
  private PreparedStatement prepareInsertUserStatement()
  {
    String preparedSql = "INSERT INTO \"Sep2\".user (userID,firstName,lastName) " +
        "SELECT * FROM (SELECT ?,?,?,?) AS tmp " +
        "WHERE NOT EXISTS (SELECT userID FROM \"Sep2\".employee " +
        "WHERE userID = ?) LIMIT 1;";
    PreparedStatement userStatement = null;

    try {
      userStatement = connection.prepareStatement(preparedSql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return userStatement;

  }

  private PreparedStatement prepareDeleteUserStatement()
  {
    String preparedSql = "DELETE FROM \"Sep2\".user " +
        "where employeeID = ? ;";

    deleteUser = null;

    try {
      deleteUser = connection.prepareStatement(preparedSql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return deleteUser;
  }


  //TODO i think its here we are closing the connection ?



}
