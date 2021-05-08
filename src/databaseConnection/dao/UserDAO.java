package databaseConnection.dao;

import shared.transferobjects.User;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO
{
  void create(String userName, String email,String password) throws
      SQLException;
  List<User> getByName(String name) throws SQLException;
  User getById(int userId) throws SQLException;
  void deleteUser(User user);
  User login(String userName, String password) throws LoginException;
}
