package databaseConnection.dao;

import shared.transferobjects.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO
{
  User create(String name) throws
      SQLException;
  List<User> getByName(String name) throws SQLException;
  User getById(int userId) throws SQLException;
  void deleteUser(User user);
}
