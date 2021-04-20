package databaseConnection.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class MovieDAOImplTest
{
    @Test
    public void testCreate() throws SQLException {
      MovieDAO movieDAO = new MovieDAOImpl();
      movieDAO.create("Spiderman");

    }


}