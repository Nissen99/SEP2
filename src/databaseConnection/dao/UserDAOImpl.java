package databaseConnection.dao;

import shared.transferobjects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends BaseDAO implements UserDAO
{
  @Override public User create(String name) throws SQLException
  {
    {
      try (Connection connection = getConnection())
      {
        PreparedStatement statement = connection
            .prepareStatement("INSERT INTO User_ (fName) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);
        statement.executeUpdate();
        ResultSet keys = statement.getGeneratedKeys();
        if (keys.next()) {
          return new User(keys.getInt("userId"), name);
        } else {
          throw new SQLException("No keys generated");
        }
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
      retur.add(new User(result.getInt("userId"), result.getString("fName")));
      return retur;

    }
  }

  @Override public User getById(int userId) throws SQLException
  {
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM User_ WHERE userId = ?");
      statement.setInt(1, userId);
      ResultSet resultSet = statement.executeQuery();
      User user = null;
      if (resultSet.next()){
        user = new User(
            resultSet.getInt("userId"),
            resultSet.getString("fName")
            );
      }
      return user;
    }


  }

  @Override public void deleteUser(User user)
  {
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("DELETE FROM User_ WHERE userid = ?");
      statement.setInt(1, user.getUserID());
      statement.executeUpdate();
    }catch (NullPointerException ignored){}
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
}
