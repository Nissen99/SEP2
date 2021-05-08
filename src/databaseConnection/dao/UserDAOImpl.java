package databaseConnection.dao;

import shared.transferobjects.User;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends BaseDAO implements UserDAO
{
  @Override public void create(String userName, String email,String password) throws SQLException
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

    }
  }

  @Override public List<User> getByName(String name) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection
          .prepareStatement("select * from User_ where fName = ?");
      stm.setString(1, name);

      ResultSet result = stm.executeQuery();
      List<User> retur = new ArrayList<>();
      while (result.next())
      {
      }
      retur.add(new User(result.getInt("userId"), result.getString("userName"),
          result.getString("email"),result.getString("password")));
      return retur;

    }
  }

  @Override public User getById(int userId) throws SQLException
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

  }

  @Override public void deleteUser(User user)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM User_ WHERE userid = ?");
      statement.setInt(1, user.getUserID());
      statement.executeUpdate();
    }
    catch (NullPointerException ignored)
    {
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public User login(String userName, String password)
      throws LoginException
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
      e.printStackTrace();
    }
    throw  new LoginException("This account has not been registered");
  }
}
