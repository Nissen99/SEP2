package databaseConnection.dao;

import org.junit.jupiter.api.Test;
import shared.Movie;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.SQLException;
import java.util.ArrayList;

class MovieDAOImplTest
{
    @Test
    public void testCreate() throws SQLException {
      MovieDAO movieDAO = new MovieDAOImpl();
      movieDAO.create("Spiderman");
      ArrayList<Movie> movieArrayList = movieDAO.getAllMovies();

      assertEquals("Spiderman", movieArrayList.get(movieArrayList.size()-1).getMovieTitle());

    }


}