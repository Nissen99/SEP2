package databaseConnection.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResetDAO extends BaseDAO
{

  public void resetDB()
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DROP TABLE IF EXISTS User_ CASCADE");
      statement.executeUpdate();
      statement = connection.prepareStatement(
          "CREATE TABLE IF NOT EXISTS User_\n" + "(\n"
              + "    userId SERIAL PRIMARY KEY,\n" + "    fName  VARCHAR(25),\n"
              + "    lName  VARCHAR(25),\n"
              + "    DOB    dateEU CHECK ( DATE_PART('year', DOB) BETWEEN 1900 AND DATE_PART('year', NOW()))\n"
              + ")");
      statement.executeUpdate();

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

}
