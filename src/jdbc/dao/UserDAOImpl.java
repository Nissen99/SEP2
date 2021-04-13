package jdbc.dao;

import shared.transferobjects.Booking;
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
            .prepareStatement("INSERT INTO User_ (name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);
        statement.executeUpdate();
        ResultSet keys = statement.getGeneratedKeys();
        if (keys.next()) {
          return new User(keys.getInt(1), name);
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
          .prepareStatement("select * from user_ where firstname = ?");
      stm.setString(1, name);

      ResultSet result = stm.executeQuery();
      List<User> retur = new ArrayList<>();
      while (result.next())
      {
      }
      retur.add(new User(result.getInt("id"), result.getString("name")));
      return retur;

    }
  }

  @Override public User getById(int userId) throws SQLException
  {
    return null;
  }
}
