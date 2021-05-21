package shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.transferobjects.Movie;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest
{

  Movie movie1, movie2;

  @BeforeEach
  public void setup() {
    movie1 = new Movie(1, "Batman" );
    movie2 = new Movie("Superman");
  }

  @Test
  void equalsTjekkerIdOgTitleErEns(){
    Movie is = new Movie(1, "Batman");
    Movie is2 = new Movie("Superman");

    assertEquals(movie1, is);
    assertEquals(movie2, is2);
  }

  @Test
  void equalsTjekkerIdForskelligt(){
    Movie is = new Movie(2, "Batman");

    assertNotEquals(movie1, is);
  }

  @Test
  void equalsTjekkerTitleForskelligt(){
    Movie is = new Movie(1, "Spiderman 3");
    Movie is2 = new Movie("Thor");

    assertNotEquals(movie1, is);
    assertNotEquals(movie2, is2);
  }

  @Test
  public void constuctorSkalOpretteMovie() {
    assertNotNull(movie1);
    assertNotNull(movie2);
  }

  @Test
  public void consSkalSætteTitel() {
    assertEquals("Batman", movie1.getMovieTitle());
    assertEquals("Superman", movie2.getMovieTitle());

  }

  @Test
  public void constructorSKalsætteId() {
    assertEquals(1, movie1.getMovieId());
    assertEquals(0, movie2.getMovieId());
  }
}