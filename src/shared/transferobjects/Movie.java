package shared.transferobjects;

import java.io.Serializable;

public class Movie implements Serializable
{
  private String movieTitle;
  private int movieId;

  public Movie(int movieId, String movieTitle)
  {
    this.movieTitle = movieTitle;
    this.movieId = movieId;
  }

  public Movie(String movieTitle)
{
  this.movieTitle = movieTitle;
}

  public String getMovieTitle()
  {
    return movieTitle;
  }

  public int getMovieId()
  {
    return movieId;
  }
}
