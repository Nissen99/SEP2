package shared.transferobjects;

import java.util.UUID;

public class Movie
{
  private String movieTitle;
  private String movieID;

  public Movie(String movieTitle)
  {
    this.movieTitle = movieTitle;
    this.movieID = UUID.randomUUID().toString();
  }
}
