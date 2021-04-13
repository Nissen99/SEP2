package jdbc.dao;

import org.junit.jupiter.api.Test;
import shared.transferobjects.Movie;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class MovieaTest
{
    @Test
    public void testCreate() throws SQLException {
      MovieDAO movieDAO = new MovieDAOImpl();
      movieDAO.create("Spiderman");

    }


}