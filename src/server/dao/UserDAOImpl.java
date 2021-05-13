package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.User;
import javax.security.auth.login.LoginException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends BaseDAO implements UserDAO
{
  @Override public void create(String userName, String email,String password)
      throws ServerException
  {
    {
      try (Connection connection = getConnection())
      {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO User_ (userName, email, password) VALUES (?,?,?)");
        statement.setString(1, userName);
        statement.setString(2, email);
        statement.setString(3, password);
        statement.executeUpdate();
      }
      catch (SQLException throwables)
      {

        throw new ServerException(throwables.getMessage());

      }

    }
  }



  @Override public User getById(int userId) throws ServerException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM User_ WHERE userId = ?");
      statement.setInt(1, userId);
      ResultSet resultSet = statement.executeQuery();
      User user = null;
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

  @Override public User login(String userName, String password)
      throws ServerException

  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM user_ WHERE userName = ? AND password = ?");
      statement.setString(1, userName);
      statement.setString(2, password);
      ResultSet resultSet = statement.executeQuery();
      User user = null;
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
