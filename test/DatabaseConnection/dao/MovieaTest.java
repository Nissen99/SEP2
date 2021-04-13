package DatabaseConnection.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class MovieaTest
{
    @Test
    public void testCreate() throws SQLException {
      MovieDAO movieDAO = new MovieDAOImpl();
      movieDAO.create("Spiderman");

    }


}