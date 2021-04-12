package jdbc.dao;

import shared.transferobjects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends BaseDAO implements UserDAO
{
  @Override public User create(String name, int userId) throws SQLException
  {
    {
      try (Connection connection = getConnection())
      {
        PreparedStatement statement = connection
            .prepareStatement("INSERT INTO User (name, userId) VALUES (?, ?)");
        statement.setString(1, name);
        statement.setInt(2, userId);
        statement.executeUpdate();
        return new User(name, userId);
      }
    }
  }

  @Override public List<User> getByName(String name) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection
          .prepareStatement("select * from user where firstname = ?");
      stm.setString(1, name);

      ResultSet result = stm.executeQuery();
      List<User> retur = new ArrayList<>();
      while (result.next())
      {
      }
      retur.add(new User(result.getString("name"), result.getInt("id")));
      return retur;

    }
  }

  @Override public User getById(int userId) throws SQLException
  {
    return null;
  }
}
