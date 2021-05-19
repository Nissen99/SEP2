package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.IUser;
import shared.transferobjects.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl extends BaseDAO implements UserDAO
{

  /**
   * Der bliver i databasen tjekket efter gyldige inputs eks. mail indeholder "@"
   * @param userName Unik brugernavn
   * @param email email der indeholder "@"
   * @param password password der har både stor og små bogstaver, og mindst 5 char lang
   * @return Den oprettede user
   * @throws ServerException problem i forbindelsen til databasen
   */
  @Override public IUser create(String userName, String email,String password)
      throws ServerException
  {
    {
      try (Connection connection = getConnection())
      {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO User_ (userName, email, password) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, userName);
        statement.setString(2, email);
        statement.setString(3, password);
        statement.executeUpdate();

        ResultSet keys = statement.getGeneratedKeys();
        if (keys.next())
        {
          return new User(keys.getInt("userId"),
              keys.getString("userName"), keys.getString("email"),
              keys.getString("password"));
        }
        else
        {
          throw new SQLException();
        }
      }
      catch (SQLException throwables)
      {
        throw new ServerException("Database fejl - " + throwables.getMessage());
      }
    }
  }


  @Override public IUser getById(int userId) throws ServerException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM User_ WHERE userId = ?");
      statement.setInt(1, userId);
      ResultSet resultSet = statement.executeQuery();
      IUser user = null;
      if (resultSet.next())
      {
        user = new User(resultSet.getInt("userId"),
            resultSet.getString("userName"), resultSet.getString("email"),resultSet.getString("password"));
      }
      return user;
    }
    catch (SQLException throwables)
    {
      throw new ServerException("Database connection failed");

    }

  }

  @Override public IUser login(String userName, String password)
      throws ServerException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM user_ WHERE userName = ? AND password = ?");
      statement.setString(1, userName);
      statement.setString(2, password);
      ResultSet resultSet = statement.executeQuery();
      IUser user = null;
      if (resultSet.next())
      {
        user = new User(resultSet.getInt("userId"),
            resultSet.getString("userName"), resultSet.getString("email"),
            resultSet.getString("password"));
        return user;
      }
    }
    catch (SQLException e)
    {
      throw new ServerException("Database connection failed");
    }
    throw new ServerException("This account has not been registered");

  }
}
