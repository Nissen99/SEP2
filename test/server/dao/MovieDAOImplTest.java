package server.dao;

import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import shared.transferobjects.Movie;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.SQLException;
import java.util.ArrayList;

class MovieDAOImplTest
{
    @Test
    public void testCreate() throws SQLException, ServerException
    {
      MovieDAO movieDAO = new MovieDAOImpl();
      movieDAO.create("Spiderman");
      ArrayList<Movie> movieArrayList = movieDAO.getAllMovies();

      assertEquals("Spiderman", movieArrayList.get(movieArrayList.size()-1).getMovieTitle());

    }


}